package com.lowt.baabackend.controller;


import com.lowt.baabackend.entity.BaaGh;
import com.lowt.baabackend.service.BaaGhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/baabackend/baaGh")
public class BaaGhController {
    @Autowired
    BaaGhService baaGhService;

    @GetMapping("{p_id}")
    public ResponseEntity<List<BaaGh>> baaGhList(@PathVariable(name = "p_id") Long pId) {
        try {
            return ResponseEntity.ok(baaGhService.personGhList(pId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("add")
    public ResponseEntity<BaaGh> addBaaGh(@RequestBody BaaGh baaGh) {
        try {
            baaGhService.save(baaGh);
            return ResponseEntity.ok(baaGh);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<BaaGh> updateBaaGh(@RequestBody BaaGh baaGh) {
        try {
            if (baaGhService.updateById(baaGh)) {
                return ResponseEntity.ok(baaGh);
            }
            throw new Exception();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<BaaGh> deleteBaaGh(@RequestBody BaaGh baaGh) {
        try {
            if (baaGhService.removeById(baaGh)) {
                return ResponseEntity.ok(baaGh);
            }
            throw new Exception();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

