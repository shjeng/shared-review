package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.common.customexception.NonExistBoardException;
import com.sreview.sharedReview.domain.dto.object.*;
import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CommentWriteRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.*;
import com.sreview.sharedReview.domain.jpa.entity.*;
import com.sreview.sharedReview.domain.jpa.service.*;
import com.sreview.sharedReview.domain.service.BoardService;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepoService boardRepoService;
    private final FavoriteRepoService favoriteRepoService;
    private final CategoryRepoService categoryRepoService;
    private final UserEntityService userEntityService;
    private final TagRepoService tagRepoService;
    private final CommentRepoService commentRepoService;
    // get
    @Override
    public ResponseDto getFaoviteBoardTop3(String condition) {
        try {
            List<Board> favoriteBoardTop3 = new ArrayList<>();
            List<BoardDto> list = new ArrayList<>();
            if(condition.equals("week")){
                favoriteBoardTop3 = boardRepoService.findFavoriteBoardTop3();
            } else{
                favoriteBoardTop3 = boardRepoService.findFavoriteBoardTop3(condition);
            }
            list = favoriteBoardTop3.stream().map(l -> new BoardDto().of(l)).toList();
            return BoardListResponse.success(list, condition);
        } catch (Exception e) {
            e.printStackTrace();
            return null; //  나중에 에러처리 어떻게 할지 정하면 지울 예정
        }
    }

    @Override
    public ResponseDto findBoardByUser(String userEmail) {
        try {

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseDto getBoardListLatest() {
        try {
            List<Board> latestBoards = boardRepoService.findLatestBoards();
            List<BoardDto> boardDtos = latestBoards.stream().map(l -> new BoardDto().of(l)).toList();
            return BoardListResponse.success(boardDtos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }


    @Override
    public ResponseEntity<? super CategoryWriteResponse> saveCategory(CategoryWriteRequest request) {
        try{
            String email = "";
            Optional<User> userOptional = userEntityService.findByEmail(email);
            if(userOptional.isEmpty()) return CategoryWriteResponse.notExistedUser();
            User user = userOptional.get();

            String getName = request.getName();
            Category category = new Category(getName,user);
            categoryRepoService.save(category);
        }catch (Exception e){
            e.printStackTrace();

        }
        return CategoryWriteResponse.success();
    }

    @Override
    public ResponseEntity<? super GetCategorysResponse> getCategorys() {
        List<CategoryDto> categorys;
        try{
            List<Category> getCategorys = categoryRepoService.findAll();
            categorys = CategoryDto.ofList(getCategorys);
        }catch (Exception e){
            e.printStackTrace();
            return GetCategorysResponse.databaseError();

        }
        return GetCategorysResponse.success(categorys);
    }

    @Override
    public ResponseEntity<? super AdminCategotyResponse> getAdminCategorys() {
        List<AdminCategoryDto> categorys;
        try{
            List<Category> getCategorys = categoryRepoService.findAll();
            categorys = AdminCategoryDto.ofList(getCategorys);
            System.out.println("categorys : " + categorys);
        }catch (Exception e){
            e.printStackTrace();
            return AdminCategotyResponse.databaseError();

        }
        return AdminCategotyResponse.success(categorys);
    }

    @Override
    public ResponseDto increaseViewcount(Long boardId) {
        Optional<Board> boardOptional = boardRepoService.findById(boardId);
        if (boardOptional.isEmpty()) {
            throw new NonExistBoardException("존재하지 않는 게시물입니다.");
        }
        Board board = boardOptional.get();
        board.increaseViewCount();
        boardRepoService.save(board);
        return IncreaseViewCountResponse.success(board.getViewsCount());
    }

    // post
    @Override
    public ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request,String email) {
        try {
            Optional<User> userOptional = userEntityService.findByEmail(email);
            if(userOptional.isEmpty()) return BoardWriteResponse.notExistedUser();

            Optional<Category> categoryOptional = categoryRepoService.findById(request.getCategory().getCategoryId());
            if (categoryOptional.isEmpty()) {   // 카테고리가 DB에 없는 경우
                throw new RuntimeException("존재하지 않는 카테고리.");
            }

            Category category = categoryOptional.get();
            User user = userOptional.get();
            Board board = BoardWriteRequest.getBoard(request);
            board.setUserAndCategory(user,category); // 글 작성자와 태그 넣어서 저장해주기
            List<Tag> tagList = request.getTagList(board);
            tagRepoService.saveAll(tagList); // 태그 저장
            boardRepoService.save(board); // 게시물 저장

        } catch (Exception e){
            e.printStackTrace();
            BoardWriteResponse.databaseError();
        }
        return BoardWriteResponse.success();
    }

    @Override
    public ResponseDto getBoard(Long boardId) {
        try {
            Optional<Board> boardOptional = boardRepoService.findBoardAndCommentsUserById(boardId);
            if (boardOptional.isEmpty()) {
                throw new NonExistBoardException("존재하지 않는 게시물입니다.");
            }
            Board board = boardOptional.get();
            User writer = board.getUser();
            UserDto userDto = UserDto.of(writer); // 작성자

            BoardDetailDto boardDetailDto = new BoardDetailDto();
            boardDetailDto.ofEntity(board); // 게시물 상세 내용

            List<Comment> comments = board.getComments(); // 댓글 리스트 가져오기
            List<CommentDto> commentDtos = new ArrayList<>();
            comments.forEach(c -> commentDtos.add(new CommentDto().of(c, userDto))); // 댓글 리스트
            List<Favorite> favorites = favoriteRepoService.findAllByBoard(board);
            List<FavoriteDto> favoriteDtos = new ArrayList<>();
            favorites.forEach(f -> favoriteDtos.add(FavoriteDto.of(f))); // 게시물 좋아요 리스트

            List<TagDto> tagDtos = new ArrayList<>();
            List<Tag> allByBoard = tagRepoService.findAllByBoard(board);
            allByBoard.stream().forEach(t->tagDtos.add(new TagDto().ofEntity(t)));

            return BoardDetailResponse.success(userDto, boardDetailDto, commentDtos, favoriteDtos,tagDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto getAllBoards(Pageable pageable) {
        try {
            Page<Board> allBoards = boardRepoService.findAll(pageable); // 모든 게시물 가져오기
            Page<BoardDto> result = allBoards.map(b -> new BoardDto().of(b));
            return BoardListResponse.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }
    @Override
    public ResponseEntity<? super AdminBoardListResponse> getAdminBoards() {
        List<AdminBoardDto> boards;
        try{
            List<Board> getBoards = boardRepoService.findAll();
            boards = AdminBoardDto.ofList(getBoards);
//            List<AdminBoardDto> boardDtos = getBoards.stream().map(l -> new AdminBoardDto().of(l)).toList(); // BoardDto로 변환
//            System.out.println("AdminBoardListResponse.success(boardDtos); : " + AdminBoardListResponse.success(boardDtos));
//            return AdminBoardListResponse.success(boardDtos);
        }catch (Exception e){
            e.printStackTrace();
            throw new InternalException();
        }
        return AdminBoardListResponse.success(boards);
    }

    @Override
    public ResponseDto commentWrite(String writerEmail ,CommentWriteRequest request) {
        try {
            Optional<User> userOptional = userEntityService.findByEmail(writerEmail);
            if (userOptional.isEmpty()) {
                throw new RuntimeException("존재하지 않는 유저입니다.");
            }
            Comment comment = new Comment();
            Optional<Board> boardOptional = boardRepoService.findById(request.getBoardId());
            if (boardOptional.isEmpty()) {
                throw new RuntimeException("존재하지 않는 게시물입니다.");
            }
            Board board = boardOptional.get();
            comment.setUserBoardContent(userOptional.get(), board, request.getContent());
            commentRepoService.save(comment);
            if (request.getCurrentPage() == null) {
                request.setCurrentPage(0);
            }
            Pageable pageable = PageRequest.of(request.getCurrentPage(), 30);

            Page<Comment> commentsByBoard = commentRepoService.findCommentsByBoard(board, pageable);
            Page<CommentDto> result = commentsByBoard.map(c -> CommentDto.of(c, UserDto.of(c.getUser())));
            return CommentResponse.success(result);
        } catch (Exception e) {
            e.printStackTrace();
//            return null;
            throw new RuntimeException("에러입니다.");
        }
    }
}



