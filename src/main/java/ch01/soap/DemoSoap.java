package ch01.soap;

import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DemoSoap {

    private static final String LOCAL_NAME = "TimeRequest";
    private static final String NAMESPACE = "http://ch01/mysoap/";
    private static final String NAMESPACE_PREFIX = "ms";

    public static void main(String[] args){
        new DemoSoap().request();
    }

    private void request() {
        try{
            // Build a SOAP message to send to a output stream
            SOAPMessage message = createSoapMessage();

            // Inject the appropiate information into the message.
            // In this case, only the (optional) message header is used.
            // and the body is empty
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            SOAPHeader header = envelope.getHeader();

            // Add an element to the SOAP header.
            Name lookUpName = createQName(message);
            header.addHeaderElement(lookUpName).addTextNode("time_request");

            // Simulate the sending of the SOAP message to a remote system by
            // writting it to a ByteArrayOutputStream.
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            message.writeTo(outputStream);

            trace("The sent SOAP message", message);


        } catch (SOAPException | IOException e){
            e.printStackTrace();
        }



    }

    private void trace(String string, SOAPMessage message) {
        System.out.println("\n");
        System.out.println(string);
        try {
            message.writeTo(System.out);
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
    }

    private Name createQName(SOAPMessage message) {
        Name name = null;
        try {
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            name = envelope.createName(LOCAL_NAME, NAMESPACE_PREFIX, NAMESPACE);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return name;
    }

    private SOAPMessage createSoapMessage() {
        SOAPMessage soapMessage = null;
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            soapMessage = messageFactory.createMessage();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return soapMessage;
    }
}
