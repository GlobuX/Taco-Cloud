package ru.globux.tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import ru.globux.tacos.Taco;


public interface TacoRepository 
         extends PagingAndSortingRepository<Taco, Long> {

}
