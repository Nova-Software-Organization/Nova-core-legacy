/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Cart.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Domain.service.user.UserService;
import com.api.apibackend.modules.Cart.Application.DTOs.CartResponse;
import com.api.apibackend.modules.Cart.Application.DTOs.ConfirmCartRequest;
import com.api.apibackend.modules.Cart.Application.converter.CartResponseConverter;
import com.api.apibackend.modules.Cart.Application.services.ICartService;
import com.api.apibackend.modules.Cart.Infra.persistence.entity.CartEntity;
import com.api.apibackend.modules.Cart.Infra.persistence.repository.CartRepository;
import com.api.apibackend.modules.CartItem.Application.DTOs.CartItemDTO;
import com.api.apibackend.modules.CartItem.Infra.persistence.entity.CartItemEntity;
import com.api.apibackend.modules.Customer.Domain.service.CustomerService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.ProductVariant.Domain.service.ProductVariantGetByIdService;
import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;
import com.api.apibackend.shared.error.exception.InvalidArgumentException;
import com.api.apibackend.shared.error.exception.ResourceNotFoundException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final CartResponseConverter cartResponseConverter;
    private final ProductVariantGetByIdService productVariantGetByIdService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           CustomerService customerService,
                           CartResponseConverter cartResponseConverter,
                           UserService userService,
                           ProductVariantGetByIdService productVariantGetByIdService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.cartResponseConverter = cartResponseConverter;
        this.productVariantGetByIdService = productVariantGetByIdService;
    }

    @Override
    public CartResponse addToCart(Long productVariantId, Integer amount) {
        CustomerEntity customer = customerService.getCustomer();
        CartEntity cart = customer.getCartEntity();

        if (Objects.nonNull(cart) && Objects.nonNull(cart.getCartItems()) && !cart.getCartItems().isEmpty()) {
            Optional<CartItemEntity> cartItem = cart.getCartItems()
                    .stream()
                    .filter(ci -> ci.getProductVariant().getId().equals(productVariantId)).findFirst();
            if (cartItem.isPresent()) {
                if (cartItem.get().getProductVariant().getStock() < (cartItem.get().getAmount() + amount)) {
                    throw new InvalidArgumentException("Produto não possui estoque desejado.");
                }
                cartItem.get().setAmount(cartItem.get().getAmount() + amount);
                CartEntity updatedCart = calculatePrice(cart);
                cart = cartRepository.save(updatedCart);
                return cartResponseConverter.apply(cart);
            }
        }

        if (Objects.isNull(cart)) {
            cart = createCart(customer);
        }

        ProductVariantEntity productVariant = productVariantGetByIdService.findProductVariantById(productVariantId);
        if (productVariant.getStock() < amount) {
            throw new InvalidArgumentException("Produto não possui estoque desejado.");
        }

        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setAmount(amount);
        cartItem.setProductVariant(productVariant);
        cartItem.setCartEntity(cart);

        if (Objects.isNull(cart.getCartItems())) {
            cart.setCartItems(new ArrayList<>());
        }
        cart.getCartItems().add(cartItem);
        cart = calculatePrice(cart);

        cart = cartRepository.save(cart);
        return cartResponseConverter.apply(cart);
    }

    @Override
    public CartResponse incrementCartItem(Long cartItemId, Integer amount) {
        CustomerEntity customer = customerService.getCustomer();
        CartEntity cart = customer.getCartEntity();
        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItems()) || cart.getCartItems().isEmpty()) {
            throw new ResourceNotFoundException("Carrinho vázio");
        }

        CartItemEntity cartItem = cart.getCartItems()
                .stream()
                .filter(ci -> ci.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Items do carrinho não encontrados"));

        if (cartItem.getProductVariant().getStock() < (cartItem.getAmount() + amount)) {
            throw new InvalidArgumentException("Produto não possui estoque desejado.");
        }

        cartItem.setAmount(cartItem.getAmount() + amount);
        cart = calculatePrice(cart);
        cart = cartRepository.save(cart);
        return cartResponseConverter.apply(cart);
    }

    @Override
    public CartResponse decrementCartItem(Long cartItemId, Integer amount) {
        CustomerEntity customer = customerService.getCustomer();
        CartEntity cart = customer.getCartEntity();
        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItems()) || cart.getCartItems().isEmpty()) {
            throw new ResourceNotFoundException("Carrinho vázio");
        }

        CartItemEntity cartItem = cart.getCartItems()
                .stream()
                .filter(ci -> ci.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Itens não encontrados!"));


        if (cartItem.getAmount() <= amount) {
            List<CartItemEntity> cartItemList = cart.getCartItems();
            cartItemList.remove(cartItem);
            if (Objects.isNull(cart.getCartItems()) || cart.getCartItems().isEmpty()) {
                customer.setCartEntity(null);
                customerService.saveCustomer(customer);
                return null;
            }
            cart.setCartItems(cartItemList);
            cart = calculatePrice(cart);
            cart = cartRepository.save(cart);
            return cartResponseConverter.apply(cart);
        }

        cartItem.setAmount(cartItem.getAmount() - amount);
        cart = calculatePrice(cart);
        cart = cartRepository.save(cart);
        return cartResponseConverter.apply(cart);
    }

    @Override
    public CartResponse fetchCart() {
        CartEntity cart = customerService.getCustomer().getCartEntity();
        if (cart == null) {
            return null;
        }
        return cartResponseConverter.apply(cart);
    }

    @Override
    public CartResponse removeFromCart(Long cartItemId) {
        CustomerEntity customer = customerService.getCustomer();
        CartEntity cart = customer.getCartEntity();

        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItems()) || cart.getCartItems().isEmpty()) {
            throw new ResourceNotFoundException("Carrinho ou items do carrinho não encontrados!");
        }

        List<CartItemEntity> cartItemsList = cart.getCartItems();
        Optional<CartItemEntity> cartItem = cart.getCartItems()
                .stream()
                .filter(ci -> ci.getId().equals(cartItemId)).findFirst();
        if (cartItem.isEmpty()) {
            throw new ResourceNotFoundException("items do carrinho não encontrados!");
        }

        cartItemsList.remove(cartItem.get());

        if (Objects.isNull(cart.getCartItems()) || cart.getCartItems().isEmpty()) {
            customer.setCartEntity(cart);
            customerService.saveCustomer(customer);
            return null;
        }

        cart.setCartItems(cartItemsList);
        cart = calculatePrice(cart);
        cart = cartRepository.save(cart);
        return cartResponseConverter.apply(cart);
    }

    @Override
    @SuppressWarnings("unlikely-arg-type")
    public boolean confirmCart(ConfirmCartRequest confirmCartRequest) {
        CartEntity dbCart = customerService.getCustomer().getCartEntity();
        if (Objects.isNull(dbCart)) {
            return false;
        }
        List<CartItemEntity> dbCartItemsList = dbCart.getCartItems();
        List<CartItemDTO> cartItemsList = confirmCartRequest.getCartItems();
        if (dbCartItemsList.size() != cartItemsList.size()) {
            return false;
        }

        for (int i = 0; i < dbCartItemsList.size(); i++) {
            if (!dbCartItemsList.get(i).getId().equals(cartItemsList.get(i).getId()) &&
                    !dbCartItemsList.get(i).getAmount().equals(cartItemsList.get(i).getAmount()) &&
                    !dbCartItemsList.get(i).getProductVariant().getId().equals(cartItemsList.get(i).getId())) {
                return false;
            }
        }

        if (dbCart.getTotalPrice().equals(confirmCartRequest.getTotalPrice()) &&
                dbCart.getTotalCargoPrice().equals(confirmCartRequest.getTotalCargoPrice()) &&
                dbCart.getTotalCartPrice().equals(confirmCartRequest.getTotalCartPrice())) {
            if (Objects.nonNull(dbCart.getDiscount()) && Objects.nonNull(confirmCartRequest.getDiscount())) {
                return dbCart.getDiscount().getRoundedDiscountValue() == confirmCartRequest.getDiscount().getDiscountPercent().intValue();
            }
            return Objects.isNull(dbCart.getDiscount()) && Objects.isNull(confirmCartRequest.getDiscount());
        }
        return false;
    }
    
    @Override
    public void emptyCart() {
        CustomerEntity customer = customerService.getCustomer();
        customer.setCartEntity(null);
        customerService.saveCustomer(customer);
    }

    @Override
    public CartEntity getCart() {
        return customerService.getCustomer().getCartEntity();
    }

    @Override
    public void saveCart(CartEntity cart) {
        if (Objects.isNull(cart)) {
            throw new InvalidArgumentException("Cart is null");
        }
        cartRepository.save(cart);
    }
    @Override
    public CartEntity calculatePrice(CartEntity cart) {
        cart.setTotalCartPrice(0.0);
        cart.setTotalCargoPrice(0.0);
        cart.setTotalPrice(0.0);
    
        cart.getCartItems().forEach(cartItem -> {
            cart.setTotalCartPrice(cart.getTotalCartPrice() + (cartItem.getProductVariant().getPrice()) * cartItem.getAmount());
            cart.setTotalCargoPrice(cart.getTotalCargoPrice() + (cartItem.getProductVariant().getCargoPrice()) * cartItem.getAmount());
            cart.setTotalPrice(
                    cart.getTotalPrice() +
                            (cartItem.getProductVariant().getPrice() + cartItem.getProductVariant().getCargoPrice()) * cartItem.getAmount());
        });
    
        if (Objects.nonNull(cart.getDiscount())) {
            cart.setTotalPrice(cart.getTotalPrice() - ((cart.getTotalPrice() * cart.getDiscount().getDiscountValue()) / 100));
        }
    
        cart.setTotalPrice(roundTwoDecimals(cart.getTotalPrice()));
        cart.setTotalCargoPrice(roundTwoDecimals(cart.getTotalCargoPrice()));
        return cart;
    }
    
    private Double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.parseDouble(twoDForm.format(d));
    }

    private CartEntity createCart(CustomerEntity customer) {
        CartEntity cart = new CartEntity();
        cart.setCustomer(customer);
        return cart;
    }
}
