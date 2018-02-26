import java.util.*;
import java.util.function.Consumer;

class Main {
    public static void main(String... args) {
        // Random variables
        String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

        // Создание списка из трех почтовых сообщений.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().

                equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().

                equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().

                endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

// Обработка списка писем почтовым сервисом
        messages.stream()
                .forEachOrdered(mailService);

        // Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft")
                .equals( Arrays.asList("This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"))
                : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").
                equals(Arrays.asList("Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."))
                : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).
                equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";


        // Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        // Создание почтового сервиса, обрабатывающего зарплаты.
        MailService<Integer> salaryService = new MailService<>();

// Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).
                forEach(salaryService);

        // Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).
                equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";

        assert salaries.get(salary2.getTo()).
                equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";

        assert salaries.get(randomTo).
                equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";
    }

    // Реализуйте классы ниже
    // Всё что выше комментария "Stepik code: start" отправляться на stepik не будет,
    // поэтому и редактировать не нужно,
    // просто сделайте так чтобы Main.main() запускался без ошибок
    //Stepik code: start
    public static interface Sendable<T> {
        String getFrom();
        String getTo();
        T getContent();
    }
    public static class MailMessage implements Sendable<String> {
        // implement here
        private String from, to, message;

        public MailMessage(String from, String to, String message) {
            this.from = from;
            this.to = to;
            this.message = message;
        }

        @Override
        public String getFrom() {
            return from;
        }
        @Override
        public String getTo() {
            return to;
        }
        @Override
        public String getContent() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MailMessage that = (MailMessage) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;
            return message.equals(that.message);
        }

        @Override
        public int hashCode() {
            int result = from.hashCode();
            result = 31 * result + to.hashCode();
            result = 31 * result + message.hashCode();
            return result;
        }
    }

    public static class Salary implements Sendable<Integer> {
        private String from, to;
        private Integer salary;

        public Salary(String from, String to, Integer salary) {
            this.from = from;
            this.to = to;
            this.salary = salary;
        }
        @Override
        public String getTo() {
            return to;
        }
        @Override
        public String getFrom() {
            return from;
        }
        @Override
        public Integer getContent() {
            return salary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Salary salary1 = (Salary) o;

            if (salary != salary1.salary) return false;
            if (!from.equals(salary1.from)) return false;
            return to.equals(salary1.to);
        }

        @Override
        public int hashCode() {
            int result = from.hashCode();
            result = 31 * result + to.hashCode();
            result = 31 * result + salary;
            return result;
        }
    }

    public static class MailService <T> implements Consumer<Sendable<T>> {
        private Map<String, List<T>> map = new LinkedHashMap<String, List<T>>(){
            @Override
            public List<T> get(Object key) {
                return super.getOrDefault(key, new LinkedList<T>());
            }
        };

        public Map<String, List<T>> getMailBox(){
            return this.map;
        }

        @Override
        public void accept(Sendable<T> t) throws NullPointerException{
            String key = t.getTo();
            if (!map.containsKey(key)) map.put(key, new LinkedList<T>());
            map.get(key).add(t.getContent());
        }
    }
//Stepik code: end
}
