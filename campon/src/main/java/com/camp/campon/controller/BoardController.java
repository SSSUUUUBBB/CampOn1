package com.camp.campon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.camp.campon.dto.Board;
import com.camp.campon.dto.Camp;
import com.camp.campon.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    @GetMapping(value="/index")
    public String index(Model model) throws Exception {
        List<Board> newReviewList = boardService.newReviewList();
        model.addAttribute("newReviewList", newReviewList);
        List<Board> crlist = boardService.crlist();
        model.addAttribute("crlist", crlist);
        List<Board> newprlist = boardService.newprlist();
        model.addAttribute("newprlist", newprlist);
        List<Board> prlist = boardService.prlist();
        model.addAttribute("prlist", prlist);
        return "board/index";
    }

    @GetMapping(value="/crread")
    public String crread(Model model, int reviewNo) throws Exception {
        Board crread = boardService.crread(reviewNo);
        model.addAttribute("crread", crread);
        return "board/crread";
    }
    @GetMapping(value="/prread")
    public String prread(Model model, int prNo) throws Exception {
        Board prread = boardService.prread(prNo);
        model.addAttribute("prread", prread);
        return "board/prread";
    }
    
    // 캠핑 리뷰 등록
    @GetMapping(value="/crinsert")
    public String crinsert(Model model, int reservationNo) throws Exception{
        Camp reservation = boardService.reservation(reservationNo);
        model.addAttribute("reservation", reservation);
        return "board/crinsert";
    }

    @PostMapping(value="/crinsert")
    public String crinsertPro(@ModelAttribute Board board) throws Exception {
        int result = boardService.crinsert(board);
        int reservationNo = board.getReservationNo();
        if(result == 0) return "board/crinsert?reservationNo=" + reservationNo;
        return "redirect:/board/index";
    }
    
    // 상품 리뷰 등록
    @GetMapping(value="/prinsert")
    public String prinsert(Model model, int orderNo) throws Exception{
        Board order = boardService.order(orderNo);
        model.addAttribute("order", order);
        return "board/prinsert";
    }

    @PostMapping(value="/prinsert")
    public String prinsertPro(@ModelAttribute Board board) throws Exception {
        int result = boardService.prinsert(board);
        int orderNo = board.getOrderNo();
        if(result == 0) return "board/prinsert?orderNo=" + orderNo;
        return "redirect:/board/index";
    }

    // 캠핑 리뷰 수정
    @GetMapping(value="/crupdate")
    public String crupdate(int reviewNo, Model model) throws Exception {
        Board crread = boardService.crread(reviewNo);
        model.addAttribute("review", crread);
        return "board/crupdate";
    }
    @PostMapping(value="/crupdate")
    public String crupdatePro(@ModelAttribute Board board) throws Exception {
        int result = boardService.crupdate(board);
        int reviewNo = board.getReviewNo();
        if(result == 0) return "board/crupdate?reviewNo=" + reviewNo;
        return "redirect:/board/crread?reviewNo="+ reviewNo;
    }
    //캠핑리뷰 삭제
    @PostMapping(value="/crdelete")
    public String crdelete(int reviewNo) throws Exception {
        int result = boardService.crdelete(reviewNo);
        if(result == 0) return "board/crupdate?reviewNo=" + reviewNo;
        return "redirect:/board/index";
    }

    //상품 리뷰 수정
    @GetMapping(value="/prupdate")
    public String prupdate(int prNo, Model model) throws Exception {
        Board pr = boardService.prread(prNo);
        model.addAttribute("pr", pr);
        return "board/prupdate";
    }
    @PostMapping(value="/prupdate")
    public String prupdatePro(@ModelAttribute Board board) throws Exception {
        int result = boardService.prupdate(board);
        int prNo = board.getPrNo();
        if(result == 0) return "board/prupdate?prNo=" + prNo;
        return "redirect:/board/prread?prNo="+ prNo;
    }
    //캠핑리뷰 삭제
    @PostMapping(value="/prdelete")
    public String prdelete(int prNo) throws Exception {
        int result = boardService.prdelete(prNo);
        if(result == 0) return "board/prupdate?prNo=" + prNo;
        return "redirect:/board/index";
    }
    
    
    
    
    
    
}
