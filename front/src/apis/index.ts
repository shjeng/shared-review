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
  GetCategorysResponseDto,
  PostBoardWriteResponseDto,
} from "./response/board";
import UserList from "../types/interface/user-list.interface";
import GetBoardDetailResponseDto from "./response/board/get-board-detail.response.dto";
import CategorieList from "../types/interface/categorie-list.interface";
import GetAdminCategorysResponseDto from "./response/board/get-admin-categorys-response.dto";

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
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
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

// 회원 리스트 요청
const USER_LIST_URL = () => `${DOMAIN}/api/user/get-user-list`;
export const getUserList = async () => {
  const result = await axios
    .get(USER_LIST_URL())
    .then((response) => {
      const responseBody: UserList[] = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      return error.response.data;
    });
  return result;
};

// 카테고리 리스트 요청
// const CATEGORIE_LIST_URL = () => `${DOMAIN}/api/user/get-categorie-list`;
// export const getCategorieList = async () => {
//   const result = await axios
//     .get(CATEGORIE_LIST_URL())
//     .then((response) => {
//       const responseBody: CategorieList[] = response.data;
//       return responseBody;
//     })
//     .catch((error) => {
//       if (!error.response.data) return null;
//       return error.response.data;
//     });
//   return result;
// };

// 카테고리 리스트 요청
const CATEGORIE_LIST_URL = () => `${API_DOMAIN}/board/get-categorys`;
export const getAdminCategorysReqeust = async () => {
  const result = await axios
    .get(CATEGORIE_LIST_URL())
    .then((response) => {
      const responseBody: GetAdminCategorysResponseDto = response.data;
      console.log(
        "index.ts responseBody : ",
        JSON.stringify(responseBody, null, 2)
      ); // 여기를 수정
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
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
