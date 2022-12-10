package com.example.table.service.impl;

import com.example.table.dto.*;
import com.example.table.entity.DinningtableEntity;
import com.example.table.repository.TableRepository;
import com.example.table.service.TableService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@Service
public class TableServiceImpl implements TableService {
    @Resource
    private TableRepository tableRepository;

    private Integer queueNum=0;

    @Override
    public DinningtableEntity findByTableId(BigInteger id){
        DinningtableEntity table = tableRepository.findByTableId(id);
        //System.out.println(table);
        return table;
    }

    @Override
    public boolean setTableStatus(PutTableParam info){
        DinningtableEntity dinningtableEntity=new DinningtableEntity();
        dinningtableEntity.setTableId(info.getTable_id() );
        dinningtableEntity.setTableCapacity(info.getTable_capacity());
        dinningtableEntity.setOccupied(info.getOccupied());
        dinningtableEntity.setCustomerNumber(info.getCustomer_number());

        DinningtableEntity tem = tableRepository.findByTableId(dinningtableEntity.getTableId());
        if (tem==null) return false;

        try {
            tableRepository.saveAndFlush(dinningtableEntity);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public QueueTableRespond getQueueTable(BigInteger req)
    {
        QueueTableRespond res=new QueueTableRespond(false,null,null);
        List<DinningtableEntity> tableEntities=tableRepository.findAll();

        //Integer tot=0;
        for(DinningtableEntity tableEntity : tableEntities)
        {
            if(!(tableEntity.getOccupied()=="是"))
            {
                //tot++;
                if(tableEntity.getTableCapacity().intValue()>=req.intValue())
                {
                    res.setHas_table(true);
                    res.setTable_id(tableEntity.getTableId().toString());
                    break;
                }
            }
        }

        if(res.isHas_table()==false)
        {
            String queue;

            Random random=new Random();
            int i= random.nextInt(0,2);
            if(i==0)
            {
                queue="A";
            } else if (i==1) {
                queue="B";
            }else {
                queue="C";
            }

            queue=queue+queueNum.toString();
            queueNum++;
            res.setQueue_id(queue);
        }

        return res;
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
            for (String existType:allTableSummary2Dto.getOptions().getXaxis().getCategories()
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
                allTableSummary2Dto.getOptions().getXaxis().getCategories().add(type);
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
