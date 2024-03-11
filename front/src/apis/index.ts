import axios from "axios";
import SignInRequestDto from "./request/auth/sign-in-request.dto";
import SignInResponseDto from "./response/auth/sign-in.response.dto";
import ResponseDto from "./response/response.dto";
import { SignUpRequestDto } from "./request/auth";
import SignUpResponseDto from "./response/auth/sign-up-response.dto";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api`;
const authorization = (accessToken: string) => {
  return { headers: { Authorization: `Bearer ${accessToken}` } };
};

const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;

const CHECK_MAIL_URL = () => `${API_DOMAIN}/auth/sign-up//Checkmail`

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

// 회원가입 요청
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
export const sendEmailRequest = async (clientEmail: string) => {
  try {
    const response = await axios.post(CHECK_MAIL_URL(), {
      u_mail: clientEmail,
    });
    alert("인증번호가 발송되었습니다.");
    console.log("성공", response.data);
  } catch (error) {
    alert("인증번호 발송에 실패하였습니다.");
    console.error("실패", error);
  }
};
