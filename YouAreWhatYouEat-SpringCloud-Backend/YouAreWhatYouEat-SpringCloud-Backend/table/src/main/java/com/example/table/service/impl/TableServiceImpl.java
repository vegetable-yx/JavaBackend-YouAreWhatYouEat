package com.example.table.service.impl;

import com.example.table.dto.AllTableInfoDto;
import com.example.table.dto.AllTableSummary2Dto;
import com.example.table.dto.AllTableSummaryDto;
import com.example.table.dto.TableInfoDto;
import com.example.table.entity.DinningtableEntity;
import com.example.table.repository.TableRepository;
import com.example.table.service.TableService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {
    @Resource
    private TableRepository tableRepository;

    @Override
    public DinningtableEntity findByTableId(BigInteger id){
        DinningtableEntity table = tableRepository.findByTableId(id);
        //System.out.println(table);
        return table;
    }

    @Override
    public AllTableInfoDto findAllTables(){
        List<DinningtableEntity> tableEntities=tableRepository.findAll();
        AllTableInfoDto result=new AllTableInfoDto();
        ModelMapper modelMapper = new ModelMapper();


        int ava = 0;
        int occ = 0;
        int tot = 0;
        int today_cnt = 0;
        int tot_cnt = 0;

        AllTableSummary2Dto allTableSummary2Dto=new AllTableSummary2Dto();
        for(DinningtableEntity tableEntity : tableEntities) {
            TableInfoDto tableInfoDto = new TableInfoDto();
            tableInfoDto.setTable_id(tableEntity.getTableId().intValue());
            tableInfoDto.setTable_capacity(tableEntity.getTableCapacity().intValue());
            tableInfoDto.setOccupied(tableEntity.getOccupied());
            tableInfoDto.setCustomer_number(tableEntity.getCustomerNumber().intValue());

            if (tableInfoDto.getOccupied()== "是") {
                today_cnt+=tableInfoDto.getCustomer_number();
                tot_cnt+=tableInfoDto.getCustomer_number();
                occ++;
            }
            else {
                tot_cnt+=tableInfoDto.getCustomer_number();
                ava++;
            }
            tot++;

            result.addTable(tableInfoDto);


            String type = tableInfoDto.getTable_capacity().toString() + "人座";
            boolean match=false;
            int index=0;
            for (String existType:allTableSummary2Dto.getOptions().getCategories()
                 ) {
                if(type==existType)
                {
                    match=true;
                    break;
                }
                index++;
            }
            if(!match)
            {
                allTableSummary2Dto.getOptions().getCategories().add(type);
                allTableSummary2Dto.getSeries().get(0).getData().add(0);
                allTableSummary2Dto.getSeries().get(1).getData().add(0);
            }
            allTableSummary2Dto.getSeries().get(tableInfoDto.getOccupied()== "是"? 1:0).addNumToData(index);
        }

        AllTableSummaryDto allTableSummaryDto=new AllTableSummaryDto();
        allTableSummaryDto.setAvailable_count(ava);
        allTableSummaryDto.setOccupied_count(occ);
        allTableSummaryDto.setTotal_count(tot);
        allTableSummaryDto.setToday_customer(today_cnt);
        allTableSummaryDto.setTotal_customer(tot_cnt);




        result.setSummary(allTableSummaryDto);
        result.setSummary2(allTableSummary2Dto);
        return result;
    }
}
