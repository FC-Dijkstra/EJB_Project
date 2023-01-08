
@MessageDriven(mappedName = 'jms/HistoryMDB')
public class HistoryMDB implements MessageListener{

    @EJB
    private Reader reader;

    @EJB
    private Writer writer;

    @Override
    public void onMessage(Message message)
    {
        if (message instanceof ObjectMessage) {
            try {
                HistoryItem historyItem = (HistoryItem)((ObjectMessage)message).getObject();
                System.out.println(historyItem.ID + " | " + historyItem.delta);
            }
            catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
}
