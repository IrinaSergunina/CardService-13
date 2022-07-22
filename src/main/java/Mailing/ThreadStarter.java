package Mailing;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//стартер для потоков
public class ThreadStarter {
    public static void main(String[] args) {
        // печать текущего времени
        System.out.println("Текущее время : " + LocalDateTime.now());

        // создание объекта ScheduledExecutorService c одним потоком
        ScheduledExecutorService ses
                = Executors.newScheduledThreadPool(1);

        // стартуем поток по рассылке уведомлений через 1 секунду после запуска
        ses.schedule(new MailingThread(),1, TimeUnit.SECONDS);
        ses.shutdown();
    }
}
