package azure532.cloudapp;

//Include the following imports to use queue APIs.
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.queue.*;


public class CloudQueueController {

	private static final String storageConnectionString =
			"DefaultEndpointsProtocol=http;" +
			"AccountName=achristiansdevtest8551;" +
			"AccountKey=ydpKQ6ixWm7ZTN+sLsPnPzqaRflrYCzx+Zq8MXGOuPmD3YKB+cigotTSqD2Hf87rVLBd3JIIsi9xQTlf6+f8Cg==";

	private CloudQueue queue;
	
	//constructor to initialize queue
	public CloudQueueController(String queueName) {
		
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		       CloudStorageAccount.parse(storageConnectionString);

		   // Create the queue client.
		   CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

		   // Retrieve a reference to a queue.
		   queue = queueClient.getQueueReference(queueName);

		   // Create the queue if it doesn't already exist.
		   queue.createIfNotExists();
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
			
	}
	
	//get message from queue
	public String getMessageFromCloudQueue() {
				
		try {
			CloudQueueMessage message = queue.retrieveMessage();
			
			if(message != null) {
				String returnMessage = message.getMessageContentAsString();
				queue.deleteMessage(message);
				return returnMessage;
			}else {
				return "Køen " + queue.getName() + " er tom - fyll opp! ";
			}
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Noe gikk feil ved lesing av kø";
		}
		
	
	}
	
	
	//add message to queue
	public void addMessageToCloudQueue(String message) {
		
		CloudQueueMessage m = new CloudQueueMessage(message);
		
				try {
			queue.addMessage(m);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
