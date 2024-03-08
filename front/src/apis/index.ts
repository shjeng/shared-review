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
