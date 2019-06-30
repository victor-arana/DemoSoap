package ch01.soap;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class DemoSoap {
    public static void main(String[] args){
        new DemoSoap().request();
    }

    private void request() {
        // Build a SOAP message to send to a output stream
        SOAPMessage soapMessage = createSoapMessage();
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
