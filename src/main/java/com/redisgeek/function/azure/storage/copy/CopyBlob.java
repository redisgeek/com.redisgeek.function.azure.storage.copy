package com.redisgeek.function.azure.storage.copy;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CopyBlob implements Function<byte[], String> {

    @Value("${targetBlobSas}")
    private String targetBlobSas;

    @Value("${targetStorageAccountUrl}")
    private String targetStorageAccountUrl;

    static String targetContainerName = "redisgeek-target";

    static String targetBlobFilename = "export.rdb.gz";

    public String apply(byte[] blob) {
        try {
            BlobClient blobClient = new BlobClientBuilder()
                    .endpoint(targetStorageAccountUrl)
                    .sasToken(targetBlobSas)
                    .containerName(targetContainerName)
                    .blobName(targetBlobFilename)
                    .buildClient();
            try {
                blobClient.delete();
                blobClient.upload(BinaryData.fromBytes(blob));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return "Blob Copied";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
