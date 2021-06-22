package com.redisgeek.function.azure.storage.copy;

import com.azure.core.credential.TokenCredential;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.AzureAuthorityHosts;
import com.azure.identity.EnvironmentCredentialBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

@Component
public class CopyBlob implements Function<Mono<String>, Mono<String>> {

    @Value("${blobSas}")
    private String blobSas;

    @Value("${storageKey}")
    private String storageKey;

    @Value("${storageAccountName}")
    private String storageName;

    @Value("${sourceContainerName}")
    private String sourceContainer;

    public Mono<String> apply(Mono<String> request) {
        try {
            TokenCredential credential = new EnvironmentCredentialBuilder()
                    .authorityHost(AzureAuthorityHosts.AZURE_PUBLIC_CLOUD)
                    .build();
            AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);
            context.getLogger().info("Copy blob to target container");
            context.getLogger().info("Delete blob from source container");
            return Mono.just("Copy Complete");
        } catch (Exception e) {
            return Mono.just(e.getMessage());
        }
    }
}
