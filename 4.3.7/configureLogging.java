private static void configureLogging() {
    // your implementation here
    Logger.getLogger("org.stepic.java.logging.ClassA").setLevel(Level.ALL);
    Logger.getLogger("org.stepic.java.logging.ClassB").setLevel(Level.WARNING);
    Logger.getLogger("org.stepic.java").setLevel(Level.WARNING);
    
    Logger logger =  Logger.getLogger("org.stepic.java");
    logger.setLevel(Level.ALL);
    logger.setUseParentHandlers(false);
    
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new XMLFormatter());
    handler.setLevel(Level.ALL);
    logger.addHandler(handler);
}




