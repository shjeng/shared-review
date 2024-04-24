import axios from "axios";
import SignInRequestDto from "./request/auth/sign-in-request.dto";
import SignInResponseDto from "./response/auth/sign-in.response.dto";
import ResponseDto from "./response/response.dto";
import { SignUpRequestDto } from "./request/auth";
import SignUpResponseDto from "./response/auth/sign-up-response.dto";
import { NicknameDupleChkResponseDto } from "./response/auth";
import { GetLoginUserResponseDto } from "./response/user";
import { BoardWriteRequestDto } from "./request/board";
import {
  BoardListResponse,
  GetCategorysResponseDto,
  PostBoardWriteResponseDto,
} from "./response/board";
import UserList from "../types/interface/user-list.interface";
import GetBoardDetailResponseDto from "./response/board/get-board-detail.response.dto";
import CategorieList from "../types/interface/admin-categorie.interface";
import GetAdminCategorysResponseDto from "./response/board/get-admin-categorys-response.dto";
import GetAdminBoardResponseDto from "./response/board/get-admin-board-list-response.dto";
import GetUserListResponseDto from "./response/user/get-user-list-response.dto";
import IncreaseViewCountResponseDto from "./response/board/increase-view-count.response.dto";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api`;
const authorication = (accessToken: string) => {
  return { headers: { Authorization: `Bearer ${accessToken}` } };
};

const AUTH_NUMBER_URL = () => `${API_DOMAIN}/auth/sign-up/verify-email`;

// ===  Get  ===

// 로그인 요청
const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
export const signInRequest = async (requestBody: SignInRequestDto) => {
  // await : 응답이 올 때까지 기다리겠다., requestBody: 어떤 데이터를 넣을 것인지
  const result = await axios
    .post(SIGN_IN_URL(), requestBody) // 서버에 post요청
    .then((response) => {
      const responseBody: SignInResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
};

// 로그인 회원 정보 가져오기
const GET_USER_URL = () => `${API_DOMAIN}/user/get-login-user`;
export const getLoginUser = async (accessToken: string) => {
  const result = await axios
    .get(GET_USER_URL(), authorication(accessToken))
    .then((response) => {
      const responseBody: GetLoginUserResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
};

// 닉네임 중복 확인
const NICKNAME_DUPL_CHK = (nickname: string) =>
  `${API_DOMAIN}/auth/nickname-chk?nickname=${nickname}`;
export const nicknameDuplChkRequest = async (nickname: string) => {
  const result = await axios
    .get(NICKNAME_DUPL_CHK(nickname))
    .then((response) => {
      const responseBody: NicknameDupleChkResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      return errorResponse(error);
    });
  return result;
};

// 카테고리 목록 불러오기
const GET_CATEGORYS = () => `${API_DOMAIN}/board/get-categorys`;
export const getCategorysReqeust = async () => {
  const result = await axios
    .get(GET_CATEGORYS())
    .then((response) => {
      const responseBody: GetCategorysResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
};

// 관리자 페이지 카테고리 목록 불러오기
const GET_ADMIN_CATEGORYS = () => `${API_DOMAIN}/board/admin/get-categorys`;
export const getAdminCategorysReqeust = async () => {
  const result = await axios
    .get(GET_ADMIN_CATEGORYS())
    .then((response) => {
      const responseBody: GetAdminCategorysResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
};

// 게시글 불러오기
const GET_BOARD = (boardId: bigint | string) =>
  `${API_DOMAIN}/board/${boardId}`;
export const getBoardRequest = async (boardId: string | bigint) => {
  const result = await axios
    .get(GET_BOARD(boardId))
    .then((response) => {
      const responseBody: GetBoardDetailResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
};
// 최신 게시물 불러오기
const GET_BOARD_LATEST_LIST = () => `${API_DOMAIN}/board/latest`;
export const getBoardLatestList = async () => {
  const result = await axios
    .get(GET_BOARD_LATEST_LIST())
    .then((response) => {
      const responseBody: BoardListResponse = response.data;
      return responseBody;
    })
    .catch((error) => {
      return errorResponse(error);
    });
  return result;
};
const GET_FAVORITE_BOARD_TOP3 = () => `${API_DOMAIN}/board/favoriteTop3`;
export const getFavoriteBoardTop3 = async (date: string) => {
  const result = await axios
    .get(GET_FAVORITE_BOARD_TOP3(), { params: { dateCondition: date } })
    .then((response) => {
      const responseBody: BoardListResponse = response.data;
      return responseBody;
    })
    .catch((error) => {
      return errorResponse(error);
    });
  return result;
};

// 관리자 페이지(회원목록) - 회원 리스트 요청
const USER_LIST_URL = () => `${API_DOMAIN}/user/get-user-list`;
export const getUserList = async () => {
  const result = await axios
    .get(USER_LIST_URL())
    .then((response) => {
      const responseBody: GetUserListResponseDto = response.data;
      console.log("responseBody값 : ", JSON.stringify(responseBody, null, 2));
      return responseBody;
    })
    .catch((error) => {
      return errorResponse(error);
    });
  return result;
};

// 관리자 페이지(게시글목록) - 게시글 목록 요청
const ADMIN_BOARD_LIST = () => `${API_DOMAIN}/board/total-list`;
export const getAdminBoardListRequest = async () => {
  const result = await axios
    .get(ADMIN_BOARD_LIST())
    .then((response) => {
      const responseBody: GetAdminBoardResponseDto = response.data;
      console.log("받아온 데이터 콘솔 출력 : ", responseBody); // 받아온 데이터 콘솔에 출력
      console.log(
        "responseBody 구조 확인 : ",
        JSON.stringify(responseBody, null, 2)
      ); // 객체의 구조를 확인
      return responseBody;
    })
    .catch((error) => {
      return errorResponse(error);
    });
  return result;
};

// 유저 페이지
const INCREASE_VIEW_COUNT_REQUEST = (boardId: string | bigint) =>
  `${API_DOMAIN}/board/increase-view-count/${boardId}`;
export const increaseViewCountRequest = async (boardId: string | bigint) => {
  return await axios
    .get(INCREASE_VIEW_COUNT_REQUEST(boardId))
    .then((response) => {
      const responseBody: IncreaseViewCountResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      return errorResponse(error);
    });
};

// ===  post  ==== //
// 회원가입 요청
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;
export const signUpRequest = async (requestBody: SignUpRequestDto) => {
  const result = await axios
    .post(SIGN_UP_URL(), requestBody)
    .then((response) => {
      const responseBody: SignUpResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
};
// 인증 이메일 발송
const CHECK_MAIL_URL = () => `${API_DOMAIN}/auth/sign-up/Checkmail`;
export const sendEmailRequest = async (clientEmail: string) => {
  console.log("넘어온 데이터" + clientEmail);
  try {
    const response = await axios.post(CHECK_MAIL_URL(), {
      u_mail: clientEmail,
    });
    alert("인증번호가 발송되었습니다.");
    console.log("성공", response.data);
    return true;
  } catch (error) {
    alert("인증번호 발송에 실패하였습니다.");
    console.error("실패", error);
  }
};
// 인증 번호 확인
export const sendEmailAuthNumber = async (emailAuthNumber: string) => {
  // 인증번호 확인 버튼 클릭 이벤트 처리 함수
  try {
    const response = await axios.post(AUTH_NUMBER_URL(), {
      authNumber: emailAuthNumber,
    });
    alert("이메일 인증에 성공했습니다.");
    console.log("성공", response.data);
    return true;
  } catch (error) {
    alert("인증번호가 일치하지 않습니다.");
    console.error("실패", error);
  }
};
// 게시글 작성
const POST_BOARD = () => `${API_DOMAIN}/board/write`;
export const postBoard = async (
  requestBody: BoardWriteRequestDto,
  accessToken: string
) => {
  const result = await axios
    .post(POST_BOARD(), requestBody, authorication(accessToken))
    .then((response) => {
      const responseBody: PostBoardWriteResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
};
// 댓글 작성
const COMMENT_WRITE = () => `${API_DOMAIN}/board/comment`;
export const commentWrite = async (
  requestBody: Comment,
  accessToken: string
) => {
  const result = await axios
    .post(COMMENT_WRITE(), requestBody, authorication(accessToken))
    .then((response) => {
      const responseBody: ResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      return errorResponse(error);
    });
  return result;
};

const errorResponse = (error: null | any) => {
  if (!error) return null;
  const responseBody: ResponseDto = error.response.data;
  return responseBody;
};
