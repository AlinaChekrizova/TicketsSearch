package ru.netolology.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketsRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketsManagerTest {

    private static Ticket first = new Ticket(1, 15000, "LED", "VKO", 70);
    private static Ticket second = new Ticket(2, 7000, "LED", "DME", 45);
    private static Ticket third = new Ticket(3, 3000, "LED", "VKO", 100);
    private static Ticket fourth = new Ticket(4, 5000, "LED", "DME", 65);
    private static Ticket fifth = new Ticket(5, 2000, "LED", "DME", 60);

    static TicketsRepository repository = new TicketsRepository();
    @BeforeAll
    public static void init(){
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        System.out.println(repository);
    }

    @Test
    public void shouldSearchByFromAndTo(){
        TicketsManager manager = new TicketsManager(repository);
        Ticket[] actual = manager.searchBy("LED", "DME");
        Ticket[] expected = {fifth, fourth, second};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldReturnError(){
        TicketsManager manager = new TicketsManager(repository);
        Ticket[] actual = manager.searchBy("ASD", "ZXC");
        Ticket[] expected = {};
        assertArrayEquals(actual, expected);
    }
    @Test
    public void shouldSortByPrice(){
        TicketsManager manager = new TicketsManager(repository);
        Ticket[] actual = manager.searchBy("LED", "VKO");
        Ticket[] expected = {third, first};
        assertArrayEquals(actual, expected);
    }

}