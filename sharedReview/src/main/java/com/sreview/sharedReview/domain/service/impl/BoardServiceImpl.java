package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.*;
import com.sreview.sharedReview.domain.dto.request.board.BoardRequestParam;
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
import org.springframework.data.domain.Sort;
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
    public ResponseDto findBoardByUserEmail(String userEmail, Pageable pageable) {
        try {
            Page<Board> boardsEntity = boardRepoService.findBoardsByUserEmail(userEmail, pageable);
            Page<BoardDto> boardDtos = boardsEntity.map(board -> new BoardDto().of(board));
            return BoardListResponse.success(boardDtos);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseDto getComments(Long boardId, Pageable pageable) {
        try {
            Page<Comment> comments = commentRepoService.findCommentsByBoardId(boardId, pageable);
            Page<CommentDto> commentDtos = comments.map(c -> CommentDto.of(c, UserDto.of(c.getUser())));
            return CommentResponse.success(commentDtos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }

    @Override
    public ResponseDto getBoardListLatest() {
        try {
            Pageable pageable = PageRequest.of(0, 10 ,Sort.by(Sort.Direction.DESC, "id"));
            Page<Board> latestBoards = boardRepoService.findLatestBoards(pageable);
            Page<BoardDto> boardDtos = latestBoards.map(l -> new BoardDto().of(l));
            return BoardListResponse.success(boardDtos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }

    @Override
    public ResponseEntity<? super CategoryWriteResponse> saveCategory(CategoryWriteRequest request, String email) {
        try{
            User user = userEntityService.findByEmail(email);
            String getName = request.getCategoryName();
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
        }catch (Exception e){
            e.printStackTrace();
            return AdminCategotyResponse.databaseError();

        }
        return AdminCategotyResponse.success(categorys);
    }

    @Override
    public ResponseEntity<? super AdminCategotyResponse> getAdminCategorySearch(String searchValue, String inputValue) {
        List<AdminCategoryDto> categorys;
        List<Category> filteredCategorys;
        try {
//          카테고리 이름으로 들어올때
            if("categoryName".equals(searchValue)) {
                System.out.println("categoryName 실행");
                filteredCategorys = categoryRepoService.findListByName(inputValue);
//          유저 아이디로 들어올때
            } else if ("userNickname".equals(searchValue)) {
                System.out.println("findByUserNickname 실행");
                filteredCategorys = categoryRepoService.findByUserNickname(inputValue);
            }
            else {
                System.out.println("!!!!!!!!!!!데이터 못찾음!!!!!!!!!!!!");
                return null;
            }
            System.out.println("쿼리문 실행 후 filteredCategorys : " + filteredCategorys);

            // DTO로 변환
            categorys = AdminCategoryDto.ofList(filteredCategorys);

            return AdminCategotyResponse.success(categorys);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
//            return AdminCategotyResponse.databaseError();
        }
    }


    @Override
    public ResponseDto increaseViewcount(Long boardId) {
        Board board = boardRepoService.findById(boardId);

        board.increaseViewCount();
        boardRepoService.save(board);
        return IncreaseViewCountResponse.success(board.getViewsCount());
    }

    // post
    @Override
    public ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request,String email) {
        try {
            User user = userEntityService.findByEmail(email);
            Optional<Category> categoryOptional = categoryRepoService.findById(request.getCategory().getCategoryId());
            if (categoryOptional.isEmpty()) {   // 카테고리가 DB에 없는 경우
                throw new RuntimeException("존재하지 않는 카테고리.");
            }

            Category category = categoryOptional.get();
            Board board = BoardWriteRequest.getBoard(request);
            board.setUserAndCategory(user,category); // 글 작성자와 태그 넣어서 저장해주기
            List<Tag> tagList = request.getTagList(board);
            tagRepoService.saveAll(tagList); // 태그 저장
            boardRepoService.save(board); // 게시물 저장
            return BoardWriteResponse.success(board.getBoardId());
        } catch (Exception e){
            e.printStackTrace();
            throw new InternalException();
        }

    }

    @Override
    public ResponseDto getBoard(Long boardId) {
        try {
//            Optional<Board> boardOptional = boardRepoService.findBoardAndCommentsUserById(boardId);
            Board board = boardRepoService.findById(boardId);
            User writer = board.getUser();
            UserDto userDto = UserDto.of(writer); // 작성자

            BoardDetailDto boardDetailDto = new BoardDetailDto();
            boardDetailDto.ofEntity(board); // 게시물 상세 내용

            Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,"id"));
            Page<Comment> comments = commentRepoService.findCommentsByBoard(board, pageable);

            Page<CommentDto> commentDtos = comments.map(c -> CommentDto.of(c, userDto));
            List<Favorite> favorites = favoriteRepoService.findAllByBoard(board);
            List<FavoriteDto> favoriteDtos = new ArrayList<>();
            favorites.forEach(f -> favoriteDtos.add(FavoriteDto.of(f))); // 게시물 좋아요 리스트

            List<TagDto> tagDtos = new ArrayList<>();
            List<Tag> tags = tagRepoService.findAllByBoard(board);
            tags.stream().map(t -> new TagDto().ofEntity(t));

            return BoardDetailResponse.success(userDto, boardDetailDto, commentDtos, favoriteDtos,tagDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public Page<BoardDto> getBoard(BoardRequestParam boardRequestParam, Pageable pageable) {
        Page<Board> boards = boardRepoService.findList(boardRequestParam, pageable);
        return boards.map(b -> new BoardDto().of(b));

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
    public ResponseDto  favorite(Long boardId,Boolean favoriteCheck, String email) {
        try {
            if (favoriteCheck) {
                int deleted = favoriteRepoService.delete(boardId, email);
            } else {
                User user = userEntityService.findByEmail(email);
                Board board = boardRepoService.findById(boardId);
                Favorite favorite = new Favorite(user, board);
                favoriteRepoService.save(favorite);
            }
            Integer favoriteCount = favoriteRepoService.countByBoardId(boardId);
            BoardDetailResponse result = BoardDetailResponse.builder().favoriteCheck(!favoriteCheck).favoriteCount(favoriteCount).build();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseDto(ResponseCode.BAD_REQUEST, ResponseMessage.BAD_REQUEST);
    }

    @Override
    public ResponseDto getBoards(Pageable pageable, BoardRequestParam params) {
        Page<Board> boardsByParams = boardRepoService.findBoardsByParams(pageable, params);
        Page<BoardDto> result = boardsByParams.map(b -> new BoardDto().of(b));
        return BoardListResponse.success(result);
    }

    @Override
    public ResponseEntity<? super AdminBoardListResponse> getAdminBoards() {
        List<AdminBoardDto> boards;
        try{
            List<Board> getBoards = boardRepoService.findAll();
            boards = AdminBoardDto.ofList(getBoards);
        }catch (Exception e){
            e.printStackTrace();
            throw new InternalException();
        }
        return AdminBoardListResponse.success(boards);
    }

    @Override
    public ResponseEntity<? super AdminBoardListResponse> getAdminBoardSearch(String searchValue, String inputValue) {
        System.out.println("Impl - 받아온 데이터 searchValue : " + searchValue);
        System.out.println("Impl - 받아온 데이터 inputValue : " +  inputValue);

        List<AdminBoardDto> boards;
        List<Board> filteredBoard;
        try {
//          게시글 제목으로 들어올때
            if("title".equals(searchValue)) {
                System.out.println("title 실행");
                filteredBoard = boardRepoService.findByTitle(inputValue);

//          유저 아이디로 들어올때
            } else if ("nickName".equals(searchValue)) {
                System.out.println("findByUserNickname 실행");
                filteredBoard = boardRepoService.findByUserNickname(inputValue);
            }
            else {
                System.out.println("!!!!!!!!!!!데이터 못찾음!!!!!!!!!!!!");
                return null;
            }
            System.out.println("쿼리문 실행 후 filteredCategorys : " + filteredBoard);

            // DTO로 변환
            boards = AdminBoardDto.ofList(filteredBoard);

            return AdminBoardListResponse.success(boards);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
//            return AdminCategotyResponse.databaseError();
        }
    }

    @Override
    public ResponseDto commentWrite(String writerEmail ,CommentWriteRequest request, Pageable pageable) {
        try {
            User user = userEntityService.findByEmail(writerEmail);
            Comment comment = new Comment();
            Board board = boardRepoService.findById(request.getBoardId());

            comment.setUserBoardContent(user, board, request.getContent());
            commentRepoService.save(comment);
            if (request.getCurrentPage() == null) {
                request.setCurrentPage(0);
            }

            Page<Comment> commentsByBoard = commentRepoService.findCommentsByBoard(board, pageable);
            Page<CommentDto> result = commentsByBoard.map(c -> CommentDto.of(c, UserDto.of(c.getUser())));
            return CommentResponse.success(result);
        } catch (Exception e) {
            e.printStackTrace();
//            return null;
            throw new RuntimeException("에러입니다.");
        }
    }

    @Override
    public ResponseDto deleteBoard(Long boardId, String email) {
        try {
            Board board = boardRepoService.findById(boardId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
        return null;
    }

    @Override
    public void deleteComment(Long commentId, String email) {
        try {
            commentRepoService.updateDeleteStatusY(commentId, email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }

    @Override
    public ResponseEntity<? super CategoryDeleteResponse> deleteCategory(Long categoryId, String email) {
        try {
            Optional<Category> categoryOptional = categoryRepoService.findById(categoryId);
            List<Board> boards = boardRepoService.findByCategoryId(categoryId);
            if (categoryOptional.isPresent() && boards.isEmpty()) { // categoryOptional가 빈값이 아니고 boards에 값이 비어 있으면 true
                categoryRepoService.deleteById(categoryId);
                return CategoryDeleteResponse.success();
            } else {
                return CategoryDeleteResponse.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }
}



