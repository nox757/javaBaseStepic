public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
    for (int i = 0; i < 3; i++) {
        RobotConnection robot = null;
        try {
            robot = robotConnectionManager.getConnection();
            robot.moveRobotTo(toX, toY);
            break;
        } catch (RobotConnectionException e) {
            if (i == 2) throw e;
        
        } catch (Exception e){
            throw e;
           
        }
        finally {
            try {
                if (robot != null) robot.close();
            } catch (Exception e) {
            
            }
        }
      
    }
}




