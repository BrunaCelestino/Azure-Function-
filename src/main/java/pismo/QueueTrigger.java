package pismo;

import java.io.IOException;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueOutput;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;

public class QueueTrigger {
    @FunctionName("ConsumeMessage")
    public static void SendMessage(
        @ServiceBusQueueTrigger(name="message", queueName="queue1", connection="serviceBusAccount") Message message,
        @BlobOutput(name="blob", path="container/myblob", connection = "AzureWebJobsStorage", dataType = "binary") OutputBinding<String> item,
        @ServiceBusQueueOutput(name="message2", queueName="queue", connection="serviceBusAccount") OutputBinding<String> okmessage,
        final ExecutionContext context) throws IOException {
            context.getLogger().info(message.toString());
            item.setValue("teste");
            okmessage.setValue("oie");
        }
    
}