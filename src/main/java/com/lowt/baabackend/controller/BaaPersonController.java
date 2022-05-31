package com.lowt.baabackend.controller;


import com.lowt.baabackend.entity.BaaPerson;
import com.lowt.baabackend.service.Impl.BaaPersonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
@CrossOrigin
@RestController
@RequestMapping("/baabackend/baaPerson")
public class BaaPersonController {

    @Resource
    BaaPersonServiceImpl baaPersonService;

    @GetMapping("{id}")
    public ResponseEntity<BaaPerson> baaPersonInfos(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(baaPersonService.getByBaaPersonId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("list")
    public ResponseEntity<List<BaaPerson>> baaPersonList() {
        try {
            return ResponseEntity.ok(baaPersonService.list());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Transactional
    @PutMapping("add")
    public ResponseEntity<BaaPerson> addBaaPerson(@RequestBody BaaPerson baaPerson) {
        try {
            baaPersonService.save(baaPerson);
            return ResponseEntity.ok(baaPerson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    @PostMapping("update")
    public ResponseEntity<BaaPerson> updateBaaPerson(@RequestBody BaaPerson baaPerson) {
        try {
            if (baaPersonService.updateById(baaPerson)) {
                return ResponseEntity.ok(baaPerson);
            }
            throw new Exception();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<BaaPerson> deleteBaaPerson(@RequestBody List<BaaPerson> baaPersons) {
        try {
            baaPersonService.removeByIds(baaPersons);
            return ResponseEntity.ok(new BaaPerson());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

