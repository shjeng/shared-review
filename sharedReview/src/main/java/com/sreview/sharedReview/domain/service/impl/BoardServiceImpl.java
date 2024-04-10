package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.common.customexception.NonExistBoardException;
import com.sreview.sharedReview.domain.dto.object.*;
import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.BoardDetailResponse;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.GetCategorysResponse;
import com.sreview.sharedReview.domain.jpa.entity.*;
import com.sreview.sharedReview.domain.jpa.service.*;
import com.sreview.sharedReview.domain.service.BoardService;
import com.sreview.sharedReview.domain.util.MarkdownUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final MarkdownUtil markdownUtil;

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
}



