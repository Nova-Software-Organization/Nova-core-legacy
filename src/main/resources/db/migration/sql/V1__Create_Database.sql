
CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` bigint(20) NOT NULL AUTO_INCREMENT,
  `idade` int(11) DEFAULT NULL CHECK (`idade` <= 150),
  `data_nascimento` datetime(6) DEFAULT NULL,
  `cpf` varchar(100) DEFAULT NULL,
  `date_criacao` datetime(6) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `genero` varchar(20) DEFAULT NULL,
  `sobrenome` varchar(100) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `idendereco` bigint(20) DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`),
  UNIQUE KEY `UK_2hrlb4jrp4k41g2casa8qa8e3` (`idendereco`),
  UNIQUE KEY `UK_jct6ap90g91aekq11wsr5edvw` (`iduser`),
  CONSTRAINT `FKhlng7835yit2joydd1j9pdbrm` FOREIGN KEY (`iduser`) REFERENCES `usuario` (`iduser`),
  CONSTRAINT `FKm24mmv5tmaws77i4p3i9o0spg` FOREIGN KEY (`idendereco`) REFERENCES `endereco` (`idendereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `carrinho` (
  `idcarrinho` bigint(20) NOT NULL AUTO_INCREMENT,
  `idcliente` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idcarrinho`),
  KEY `FKlda51s899dorea94ikuol8ni5` (`idcliente`),
  CONSTRAINT `FKlda51s899dorea94ikuol8ni5` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `contato` (
  `idcontato` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcontato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cupom` (
  `idcoupon` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_barras_qr` varchar(100) DEFAULT NULL,
  `codigo_campanha` varchar(50) DEFAULT NULL,
  `condicoes` varchar(255) DEFAULT NULL,
  `codigo_cupom` varchar(50) DEFAULT NULL,
  `status_cupom` varchar(20) DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `tipo_cliente` varchar(50) DEFAULT NULL,
  `tipo_desconto` varchar(20) DEFAULT NULL,
  `valor_desconto` double DEFAULT NULL,
  `data_expiracao` date DEFAULT NULL,
  `usos_maximos_cliente` int(11) DEFAULT NULL,
  `valor_minimo_pedido` double DEFAULT NULL,
  `notas` varchar(255) DEFAULT NULL,
  `data_inicio` date DEFAULT NULL,
  `ativo` int(11) DEFAULT NULL,
  `informacoes_uso` varchar(255) DEFAULT NULL,
  `usos_restantes` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcoupon`),
  UNIQUE KEY `UK_63gpwevx5agq2p6q9u10fcnnl` (`codigo_cupom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `endereco` (
  `idendereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(255) DEFAULT NULL,
  `num_casa` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idendereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `endereco_fornecedor` (
  `id_ed_fornecedor` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ed_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `estoque` (
  `idestoque` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_movimentacao` datetime(6) NOT NULL,
  `quantidade_entrada` int(11) DEFAULT NULL,
  `quantidade_saida` int(11) DEFAULT NULL,
  `tipo_movimentacao` varchar(255) NOT NULL,
  `idproduto` bigint(20) DEFAULT NULL,
  `idunidade` bigint(20) NOT NULL,
  PRIMARY KEY (`idestoque`),
  UNIQUE KEY `UK_hvvn4mnphdkw2yaewu0osrn1p` (`idproduto`),
  KEY `FKjnxrv3i6isgh3t1s26666f2c2` (`idunidade`),
  CONSTRAINT `FK3wk3dtowhksa49lhxv74b34b9` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idproduto`),
  CONSTRAINT `FKjnxrv3i6isgh3t1s26666f2c2` FOREIGN KEY (`idunidade`) REFERENCES `unidade` (`idunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `estoque_movimento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_movimentacao` datetime(6) NOT NULL,
  `quantidade_saida` int(11) DEFAULT NULL,
  `idestoque` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6xg53tgnmw8rh17njgvokyw24` (`idestoque`),
  CONSTRAINT `FK6xg53tgnmw8rh17njgvokyw24` FOREIGN KEY (`idestoque`) REFERENCES `estoque` (`idestoque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `idfornecedor` bigint(20) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(255) DEFAULT NULL,
  `contato` varchar(255) DEFAULT NULL,
  `dt_criacao` datetime(6) DEFAULT NULL,
  `nm_empresa` varchar(255) DEFAULT NULL,
  `cargo_fornecedor` varchar(255) DEFAULT NULL,
  `regiao` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `id_ed_fornecedor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idfornecedor`),
  KEY `FKasvvm7jlcw5on549swq7bwyw9` (`id_ed_fornecedor`),
  CONSTRAINT `FKasvvm7jlcw5on549swq7bwyw9` FOREIGN KEY (`id_ed_fornecedor`) REFERENCES `endereco_fornecedor` (`id_ed_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `fornecedor_marca` (
  `idmarca` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `idfornecedor` bigint(20) NOT NULL,
  PRIMARY KEY (`idmarca`),
  KEY `FKmkmj5diinmdeqane6qymn4srd` (`idfornecedor`),
  CONSTRAINT `FKmkmj5diinmdeqane6qymn4srd` FOREIGN KEY (`idfornecedor`) REFERENCES `fornecedor` (`idfornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `item_carrinho` (
  `idcarrinho_item` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `idcarrinho` bigint(20) DEFAULT NULL,
  `idproduto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idcarrinho_item`),
  KEY `FKprr65e2q5e7usg3xil2amo8m4` (`idcarrinho`),
  KEY `FKo47qyojk7kn5y4kcgppgs0p3g` (`idproduto`),
  CONSTRAINT `FKo47qyojk7kn5y4kcgppgs0p3g` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idproduto`),
  CONSTRAINT `FKprr65e2q5e7usg3xil2amo8m4` FOREIGN KEY (`idcarrinho`) REFERENCES `carrinho` (`idcarrinho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exception` varchar(255) DEFAULT NULL,
  `log_date` datetime(6) DEFAULT NULL,
  `log_level` varchar(255) DEFAULT NULL,
  `logger_name` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `midia` (
  `idmid` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  `data_criacao` datetime(6) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`idmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `pedido` (
  `id_numero_pedido` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nmcliente` varchar(255) DEFAULT NULL,
  `dtpagamento` datetime(6) DEFAULT NULL,
  `metodo_pagamento` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor_total` float DEFAULT NULL,
  `idcliente` bigint(20) DEFAULT NULL,
  `idtransacao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_numero_pedido`),
  KEY `FKei9nl3d1no87q5gnfg2os6mnq` (`idcliente`),
  KEY `FKsatj4afqhaajto62t2e1g61t8` (`idtransacao`),
  CONSTRAINT `FKei9nl3d1no87q5gnfg2os6mnq` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`),
  CONSTRAINT `FKsatj4afqhaajto62t2e1g61t8` FOREIGN KEY (`idtransacao`) REFERENCES `transacao` (`idtransacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `pedido_endereco` (
  `idendereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(255) DEFAULT NULL,
  `num_casa` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `number_order` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idendereco`),
  UNIQUE KEY `UK_cumk6ahk7ce5chvliwukodns1` (`number_order`),
  CONSTRAINT `FK5ev3otix40g69odqi2m2hapjr` FOREIGN KEY (`number_order`) REFERENCES `pedido` (`id_numero_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `pedido_item` (
  `idped_item` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `preco_uni` decimal(38,2) DEFAULT NULL,
  `idnum_ped` bigint(20) DEFAULT NULL,
  `idproduto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idped_item`),
  KEY `FK70d3pcey8uoil3mhf6xenqc97` (`idnum_ped`),
  KEY `FKh0m8vd8w34sp2d068ai3gfyee` (`idproduto`),
  CONSTRAINT `FK70d3pcey8uoil3mhf6xenqc97` FOREIGN KEY (`idnum_ped`) REFERENCES `pedido` (`id_numero_pedido`),
  CONSTRAINT `FKh0m8vd8w34sp2d068ai3gfyee` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idproduto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `pedido_produto` (
  `idnum_ped` bigint(20) NOT NULL,
  `idproduto` bigint(20) NOT NULL,
  KEY `FKxny52ntsf7lop9qqw36y3nkx` (`idproduto`),
  KEY `FKpuhlj6csvpti8ucdjyj6cfq4f` (`idnum_ped`),
  CONSTRAINT `FKpuhlj6csvpti8ucdjyj6cfq4f` FOREIGN KEY (`idnum_ped`) REFERENCES `pedido` (`id_numero_pedido`),
  CONSTRAINT `FKxny52ntsf7lop9qqw36y3nkx` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idproduto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `preco_produto` (
  `idpreco` bigint(20) NOT NULL AUTO_INCREMENT,
  `moeda` varchar(255) DEFAULT NULL,
  `preco_de` decimal(38,2) DEFAULT NULL,
  `tipo_desconto` varchar(255) DEFAULT NULL,
  `data_fim_vigencia` datetime(6) DEFAULT NULL,
  `notas_observacoes` varchar(255) DEFAULT NULL,
  `preco` decimal(38,2) DEFAULT NULL,
  `origem_preco` varchar(255) DEFAULT NULL,
  `data_inicio_vigencia` datetime(6) DEFAULT NULL,
  `ativo` int(11) DEFAULT NULL,
  `unidade_medida` varchar(255) DEFAULT NULL,
  `usuario_atualizacao` varchar(255) DEFAULT NULL,
  `idproduto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idpreco`),
  UNIQUE KEY `UK_pu8x1wnufhad19r1vmy92aexl` (`idproduto`),
  CONSTRAINT `FK4byu8txnbtyf4wqog4e18iceu` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idproduto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `produto` (
  `idproduto` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sku` varchar(255) DEFAULT NULL,
  `ativo` int(11) DEFAULT NULL,
  `idcategoria` bigint(20) NOT NULL,
  `idmid` bigint(20) DEFAULT NULL,
  `idfornecedor` bigint(20) DEFAULT NULL,
  `idunidade` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idproduto`),
  UNIQUE KEY `UK_75vhyf64ow81dk2imij0gufkb` (`idmid`),
  KEY `FKfeqqrtgt9xh5babxjb7shrto1` (`idcategoria`),
  KEY `FKl23syd0lipu5s6smnx360lauj` (`idfornecedor`),
  KEY `FKhb0fkj8ltmfprr2axjrat3q5x` (`idunidade`),
  CONSTRAINT `FKfeqqrtgt9xh5babxjb7shrto1` FOREIGN KEY (`idcategoria`) REFERENCES `produto_categoria` (`idcategoria`),
  CONSTRAINT `FKhb0fkj8ltmfprr2axjrat3q5x` FOREIGN KEY (`idunidade`) REFERENCES `unidade` (`idunidade`),
  CONSTRAINT `FKl23syd0lipu5s6smnx360lauj` FOREIGN KEY (`idfornecedor`) REFERENCES `fornecedor` (`idfornecedor`),
  CONSTRAINT `FKqf5likbwp4xfratyb8ywipig` FOREIGN KEY (`idmid`) REFERENCES `midia` (`idmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `produto_categoria` (
  `idcategoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tipo_categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `transacao` (
  `idtransacao` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_num` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `parcela` varchar(255) DEFAULT NULL,
  `dt_compra` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor_total` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`idtransacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `unidade` (
  `idunidade` bigint(20) NOT NULL AUTO_INCREMENT,
  `abreviacao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`idunidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `usuario` (
  `iduser` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `token_redefinicao_senha` varchar(255) DEFAULT NULL,
  `expiracao_token_redefinicao_senha` datetime(6) DEFAULT NULL,
  `apelido` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`),
  UNIQUE KEY `UK_8v4djte0bymjdp2xj52wa0dvv` (`apelido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `usuario_roles` (
  `usuario_iduser` bigint(20) NOT NULL,
  `permicao` tinyint(4) DEFAULT NULL CHECK (`permicao` between 0 and 1),
  KEY `FK50ve56xuddjbrvleumvtm0pxj` (`usuario_iduser`),
  CONSTRAINT `FK50ve56xuddjbrvleumvtm0pxj` FOREIGN KEY (`usuario_iduser`) REFERENCES `usuario` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;