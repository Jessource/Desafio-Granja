package com.precojusto.granja;

import com.precojusto.granja.mappers.CreateDuck;
import com.precojusto.granja.mappers.DuckList;
import com.precojusto.granja.model.Duck;
import com.precojusto.granja.repositories.DuckRepository;
import com.precojusto.granja.services.DuckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DuckServiceTest {

    @InjectMocks
    private DuckService duckService;

    @Mock
    private DuckRepository duckRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void deveSalvarPatoComSucesso() {
        CreateDuck createDuck = new CreateDuck();
        createDuck.setName("Donald");
        createDuck.setSex('M');

        Duck savedDuck = new Duck();
        savedDuck.setName("Donald");
        savedDuck.setSex('M');
        savedDuck.setAvailable(true);

        Mockito.when(duckRepository.save(Mockito.any(Duck.class))).thenReturn(savedDuck);

        Duck result = duckService.save(createDuck);

        assertNotNull(result);
        assertEquals("Donald", result.getName());
        assertEquals('M', result.getSex());
        assertTrue(result.getAvailable());
        Mockito.verify(duckRepository, Mockito.times(1)).save(Mockito.any(Duck.class));
    }

    @Test
    void deveLancarErroQuandoMaeNaoExistir() {
        UUID motherId = UUID.randomUUID();
        CreateDuck createDuck = new CreateDuck();
        createDuck.setName("Donald");
        createDuck.setSex('M');
        createDuck.setMother(motherId);

        Mockito.when(duckRepository.findById(motherId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> duckService.save(createDuck));

        assertEquals("Não existe uma pata com o UUID fornecido", exception.getMessage());
        Mockito.verify(duckRepository, Mockito.times(1)).findById(motherId);
    }

    @Test
    void deveListarTodosOsPatosComSucesso() {
        Duck duck1 = new Duck();
        duck1.setName("Donald");
        Duck duck2 = new Duck();
        duck2.setName("Daisy");

        List<Duck> duckList = List.of(duck1, duck2);
        DuckList duckListDto1 = new DuckList();
        duckListDto1.setName("Donald");
        DuckList duckListDto2 = new DuckList();
        duckListDto2.setName("Daisy");

        Mockito.when(duckRepository.findAll()).thenReturn(duckList);
        Mockito.when(modelMapper.map(duck1, DuckList.class)).thenReturn(duckListDto1);
        Mockito.when(modelMapper.map(duck2, DuckList.class)).thenReturn(duckListDto2);

        List<DuckList> result = duckService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Donald", result.get(0).getName());
        assertEquals("Daisy", result.get(1).getName());
        Mockito.verify(duckRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deveRetornarPaginaDePatos() {
        Duck duck = new Duck();
        duck.setName("Donald");

        Page<Duck> duckPage = new PageImpl<>(List.of(duck));
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(duckRepository.findAll(pageable)).thenReturn(duckPage);

        Page<Duck> result = duckService.listAllDucks(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Donald", result.getContent().get(0).getName());
        Mockito.verify(duckRepository, Mockito.times(1)).findAll(pageable);
    }
}