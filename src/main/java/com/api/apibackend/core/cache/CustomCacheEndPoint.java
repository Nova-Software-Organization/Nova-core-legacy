/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.core.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.cache.CacheManager;

@Endpoint(id = "custom-cache")
public class CustomCacheEndPoint {
    
    private final CacheManager cacheManager;

    public CustomCacheEndPoint(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @ReadOperation
    public Map<String, Object> customCacheInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("cacheNames", cacheManager.getCacheNames());
        return info;
    }
}
