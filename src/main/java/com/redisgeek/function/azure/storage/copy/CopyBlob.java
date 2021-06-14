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
public class CopyBlob implements Function<Mono<Optional<String>>, Mono<String>> {

    @Value("${rg_name}")
    private String rg_name;

    @Value("${blobSas}")
    private String blobSas;

    @Value("${storageKey}")
    private String storageKey;

    @Value("${storageName}")
    private String storageName;

    @Value("${storageContainerName}")
    private String storageContainer;

    public Mono<String> apply(Mono<Optional<String>> request) {
        try {
            TokenCredential credential = new EnvironmentCredentialBuilder()
                    .authorityHost(AzureAuthorityHosts.AZURE_PUBLIC_CLOUD)
                    .build();
            AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);

            return Mono.just("Export Complete");
        } catch (Exception e) {
            return Mono.just(e.getMessage());
        }
    }
}
