package com.doyouknow.project.service;

import com.doyouknow.project.dto.BoardDTO;
import com.doyouknow.project.entity.Board;
import com.doyouknow.project.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private ModelMapper modelMapper;

    private BoardRepository boardRepository;

    public BoardService(ModelMapper modelMapper, BoardRepository boardRepository) {
        this.modelMapper = modelMapper;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void registerBoard(BoardDTO newBoard) {
        System.out.println("[BoardDTO] : " + newBoard);
        Board newBoardEntity = modelMapper.map(newBoard, Board.class);
        System.out.println("[Board] : " + newBoardEntity);
        boardRepository.save(newBoardEntity);
    }

    //행사, 공지 목록 보기
    /* 부서별 페이지 */
    public Page<BoardDTO> deptBoard(Pageable pageable, int deptSeq) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize(),
                Sort.by("seq").descending());
        Page<Board> boardList = boardRepository.findAllByDept(pageable, deptSeq);
        return boardList.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    /* 마감일 고정 목록*/
    public List<BoardDTO> top3List(int deptSeq){

        Pageable pageable = PageRequest.of(0, 3, Sort.by("applyEnd").ascending());
        List<Board> result = boardRepository.findTop(deptSeq, pageable);

        return result.stream().map(board -> modelMapper.map(board, BoardDTO.class)).toList();
    }

    /* 제목 검색 기능 */
//    @Transactional
//    public List<BoardDTO> search(String keyword){
//        List<BoardDTO> postsList = boardRepository.findTitleContaining(keyword);
//    }


}
