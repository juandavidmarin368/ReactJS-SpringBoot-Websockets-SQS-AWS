package com.websockets.WebSockets.controllers;

import java.util.List;

import com.websockets.WebSockets.Dao.MainDao;
import com.websockets.WebSockets.model.Elementos;
import com.websockets.WebSockets.model.Notifications;
import com.websockets.WebSockets.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

import com.amazonaws.services.sqs.model.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api")
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    private Notifications notifications = new Notifications(0);

    @Autowired
    MainDao cargodao;

    String returnSTRING = "";
    String id = "";
    String accessKey = "AKIAWQ5AL2DIB5QN6";
    String secretKey = "1Apd8xA+rF9DZeSzNj9cxN2618tMS4Jc0c35";
    boolean delete = false;

    @GetMapping("/notify")
    public String getNotification() {

        notifications.increment();

        template.convertAndSend("/topic/notification", notifications);

        return "Notifications successfully sent to  !";
    }

    @PostMapping({ "/deletefifoitem" })
    public String deleteFifoItem() {

        System.out.println("deleting....");

        delete = true;

        return "FIFO DELETED";

    }

    @PostMapping({ "/adduser" })
    public ResponseEntity<List<User>> createDeck(@RequestBody User payload) {

        List<User> userTemp = cargodao.getUsers(payload);

        template.convertAndSend("/topic/notification", userTemp);

        return new ResponseEntity<List<User>>(userTemp, HttpStatus.OK);

    }

    @Scheduled(fixedRate = 10000)
    public void getMessages() {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        final AmazonSQS sqs = new AmazonSQSClient(credentials);

        try {

            final Map<String, String> attributes = new HashMap<>();

            attributes.put("FifoQueue", "true");

            attributes.put("ContentBasedDeduplication", "true");

            // The FIFO queue name must end with the .fifo suffix.
            final CreateQueueRequest createQueueRequest = new CreateQueueRequest("prueba2.fifo")
                    .withAttributes(attributes);
            final String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

            System.out.println("Receiving messages from prueba2.fifo.\n");
            final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);

            final List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (final Message message : messages) {
                /*
                 * System.out.println("Message"); System.out.println("  MessageId:     " +
                 * message.getMessageId()); System.out.println("  ReceiptHandle: " +
                 * message.getReceiptHandle()); System.out.println("  MD5OfBody:     " +
                 * message.getMD5OfBody()); System.out.println("  Body:          " +
                 * message.getBody());
                 */
                returnSTRING = message.getBody();

                JSONObject obj = new JSONObject(returnSTRING);

                System.out.println("id " + obj.getString("id"));
                System.out.println("nombre " + obj.getString("nombre"));
                Elementos user = new Elementos(obj.getString("id"), obj.getString("nombre"));
                template.convertAndSend("/topic/notification", user);

            }

            if (delete) {

                if (messages.size() > 0) {
                    System.out.println("Deleting the message.\n");
                    final String messageReceiptHandle = messages.get(0).getReceiptHandle();
                    System.out.println("messageReceiptHandle " + messageReceiptHandle);
                    sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageReceiptHandle));
                    delete = false;
                }

            }

        } catch (final AmazonServiceException ase) {
            System.out.println(
                    "Caught an AmazonServiceException, which means " + "your request made it to Amazon SQS, but was "
                            + "rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (final AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means "
                    + "the client encountered a serious internal problem while "
                    + "trying to communicate with Amazon SQS, such as not " + "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

    }
    // return returnSTRING;
	
	
	/*
	
		TO SEND DATA TO THE QUEUE MUST BE SEND IT LIKE THIS:
		
		{

			"id":"7987",
			"nombre":"NewName.."

		}
		
		give it a different ID each time you send a new item

	
	*/

}
