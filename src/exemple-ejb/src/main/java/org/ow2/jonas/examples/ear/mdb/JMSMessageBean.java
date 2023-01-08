
import javax.annotation.security.RunAs;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.ow2.jonas.examples.ear.entity.Compte;
import org.ow2.jonas.examples.ear.reader.LocalReader;
import org.ow2.jonas.examples.ear.writer.LocalWriter;


@MessageDriven(name = "EJBExempleQueue", activationConfig={
        @ActivationConfigProperty(propertyName = "destinationLookup",
                                  propertyValue = "queue/SampleQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
        						  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName="maxPoolSize", 
        						  propertyValue="1"),
        @ActivationConfigProperty(propertyName="maxSession", 
        						  propertyValue="1")
        })
public class JMSMessageBean implements MessageListener {

    @EJB
    private LocalWriter writer;

    @EJB
    private LocalReader reader;

    public synchronized void onMessage(final Message message) {

        // TODO to be removed
        System.out.println("Received JMS Message: " + message);

        // Extract Message's text value
        String text = null;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                text = textMessage.getText();
            } catch (JMSException e) {
                System.err.println("Unexpected Exception: " + e.getMessage());
                e.printStackTrace(System.err);
                return;
            }
        } else {
            // not a TextMessage, I don't know what to do with it
            return;
        }

        Compte edition = reader.findCompte(1);
        if (edition == null) {
            edition = new Compte(20.0);
            writer.addCompte(edition);
        }
    }

}
