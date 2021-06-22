package com.redisgeek.function.azure.storage.copy;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;


/**
 * Azure Functions with HTTP Trigger.
 */
public class CopyBlobHandler extends FunctionInvoker<byte[], String> {

    @FunctionName("CopyBlob")
    public void run(
            @BlobTrigger(name = "file",
                    dataType = "binary",
                    path = "redisgeek-source/{name}.rdb.gz",
                    connection = "AzureWebJobsStorage") byte[] content,
            @BindingName("name") String filename,
            final ExecutionContext context
    ) {
        context.getLogger().info("Function Result :::" + handleRequest(content, context));
    }
}
