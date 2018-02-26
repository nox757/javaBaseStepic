

public static class UntrustworthyMailWorker implements MailService {
    private MailService[] mailServices;

    private RealMailService realMailService = new RealMailService();
    public UntrustworthyMailWorker(MailService[] mailServices) {
        this.mailServices = mailServices;
    }

    public RealMailService getRealMailService() {
        return this.realMailService;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mailServices.length == 0 || mailServices == null) {
            return realMailService.processMail(mail);
        }
        Sendable res = mailServices[0].processMail(mail);
        for (int i = 1; i < mailServices.length; i++) {
            res = mailServices[i].processMail(res);
        }
        return realMailService.processMail(res);        
    }
}


public static class Spy implements MailService {

    private Logger logger;

    public Spy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {

        if (mail instanceof MailMessage) {
            String from = mail.getFrom();
            String to = mail.getTo();
            String message = ((MailMessage) mail).getMessage();
            if (from.equals(AUSTIN_POWERS) || to.equals(AUSTIN_POWERS)) {

                logger.log(Level.WARNING, "Detected target mail correspondence: from " + from +" to " + to + " \"" + message +"\"");
            } else {
            
                logger.log(Level.INFO, "Usual correspondence: from " + from + " to " + to); 
            }

        }
        return mail;

    }

}


public static class Thief implements MailService {

    private int cost = -1;
    private int amount = 0;
   
    public Thief(int cost) {
            this.cost = cost;
    }

    public int getStolenValue() {
        return this.amount;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            Package currentPackage = ((MailPackage) mail).getContent();
            if (currentPackage.getPrice() >= cost) {
               
                amount += currentPackage.getPrice();
                return new MailPackage(mail.getFrom(), mail.getTo(), new Package(String.format("stones instead of %s", currentPackage.getContent()), 0));
            }

        }
        return mail;

    }

}



public static class Inspector implements MailService {

    @Override
    public Sendable processMail(Sendable mail){
        if (mail instanceof MailPackage) {
            String currentContent = ((MailPackage) mail).getContent().getContent().toLowerCase();
            if (currentContent.equals(WEAPONS) ||  currentContent.equals(BANNED_SUBSTANCE)) {
                throw new IllegalPackageException();
            }

            if (currentContent.contains("stones")) {
                 throw new StolenPackageException();
            }

        }
        return mail;
    }

}

public static class IllegalPackageException extends  RuntimeException {

}

public static class StolenPackageException extends  RuntimeException {
    
}
