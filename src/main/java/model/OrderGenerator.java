package model;

import java.util.Random;

public class OrderGenerator {


    String[] companies = new String[]{"Яндекс", "Google"}; // массив компаний
    String[] services = new String[]{"Такси", "Документы", "Почта", "Деньги"}; // массив сервисов

    int randomCompany = new Random().nextInt(companies.length); // выбор случайной компании из массива
    int randomService = new Random().nextInt(services.length); // выбор случайного сервиса из массива

// можно создать случайную комбинацию Компания.Сервис:
//System.out.format("%s.%s", companies[randomCompany], services[randomService]);
// строки отформатированы, чтобы название компании и сервиса выводились через точку

//    List<Foo> list = createItSomehow();
//    Random random = new Random();
//    Foo foo = list.get(random.nextInt(list.size()));
}