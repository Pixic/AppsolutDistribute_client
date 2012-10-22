public class TicketClient {
    private TicketPrinter ticketPrinter;
    private SharedNumberServer sharedNumberServer;

    public TicketClient(SharedNumberServer sharedNumberServer, String printerDevice) {        
        this.sharedNumberServer = sharedNumberServer;
        ticketPrinter = new TicketPrinter(printerDevice);
    } 

    public void makeTicket() {        
        ticketPrinter.print(sharedNumberServer.getNext());
    } 
 }
