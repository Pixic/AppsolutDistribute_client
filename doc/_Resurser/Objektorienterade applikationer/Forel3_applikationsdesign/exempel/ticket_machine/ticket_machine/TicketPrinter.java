public class TicketPrinter {
    private String printerDevice;

    public  TicketPrinter(String printerDevice) {        
        this.printerDevice = printerDevice;
    } 
    
    public void print(int ticketID) {        
        // create a ticket with ID ticketID 
        // and send it to the printer device
        // ... but here we cheat a bit, after all it's just a prototype
        System.out.println("Printer: " + printerDevice + 
                           ", ticket no: " + ticketID);
    } 
}
