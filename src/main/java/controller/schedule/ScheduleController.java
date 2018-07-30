package controller.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleController {

	private static ScheduleController instance;

	private ScheduledExecutorService usersExecutorService;
	private ScheduledExecutorService messagesExecutorService;

	private ScheduleController() {
	}

	public static ScheduleController getInstance() {
		return instance == null ? instance = new ScheduleController() : instance;
	}
	
	private boolean executorRunning(ScheduledExecutorService e){
		return e != null && (!e.isTerminated() || !e.isShutdown());
	}

	public boolean start() {
		if (executorRunning(usersExecutorService) && executorRunning(messagesExecutorService)) {
			return true; //Já está executando.
		}
		try{
			usersExecutorService = Executors.newSingleThreadScheduledExecutor();
			usersExecutorService.scheduleAtFixedRate(new UsersRunnable(), 0, 7, TimeUnit.SECONDS);
			
			messagesExecutorService = Executors.newSingleThreadScheduledExecutor();
			messagesExecutorService.scheduleAtFixedRate(new MessagesRunnable(), 0, 15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean stop() throws InterruptedException {
		return stop(false);
	}
	
	public boolean stop(boolean awaitTermination) throws InterruptedException {
		if (executorRunning(usersExecutorService) && executorRunning(messagesExecutorService)) {
			usersExecutorService.shutdown();
			messagesExecutorService.shutdown();
			if(awaitTermination){
				return usersExecutorService.awaitTermination(15, TimeUnit.SECONDS) 
						&& messagesExecutorService.awaitTermination(30, TimeUnit.SECONDS); //Trava a Thread
			}
			return usersExecutorService.isShutdown() && messagesExecutorService.isShutdown();
		}
		return true; //Já estava parado
	}
	
	public boolean restart() throws InterruptedException {
		return stop(true) && start();
	}

}
