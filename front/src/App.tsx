import React, { useEffect } from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Container from "./layouts/Container";
import {
  AUTH_PATH,
  BOARD_WRITE,
  MAIN_PATH,
  SIGN_IN_PATH,
  SIGN_UP_PATH,
} from "./constant";
import Main from "./views/Main";
import Authentication from "./views/Authentication";
import SignIn from "./views/Authentication/sign-in";
import SignUp from "./views/Authentication/sign-up";
import { useCookies } from "react-cookie";
import { useLoginUserStore } from "./store";
import { getLoginUser } from "./apis";
import { GetLoginUserResponseDto } from "./apis/response/user";
import ResponseDto from "./apis/response/response.dto";
import BoardWrite from "./views/BoardWrite";

function App() {
  const { setLoginUser, resetLoginUser } = useLoginUserStore();
  const [cookies, setCookies] = useCookies();

  useEffect(() => {
    if (!cookies.accessToken) {
      resetLoginUser();
      return;
    }
    getLoginUser(cookies.accessToken).then(getLoginUserResponse);
  }, [cookies.accessToken]);
  const getLoginUserResponse = (
    responseBody: GetLoginUserResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) return;
    const { code } = responseBody;
    if (code === "VF") alert("유효성 검사 실패");
    if (code === "NU") alert("존재하지 않는 유저");
    if (code === "DBE") alert("데이터베이스 오류");
    if (code !== "SU") {
      resetLoginUser();
      return;
    }
    const { userDto } = responseBody as GetLoginUserResponseDto;
    console.log(userDto);
    setLoginUser(userDto);
  };
  return (
    <Routes>
      <Route element={<Container />}>
        <Route path={MAIN_PATH()} element={<Main />} />
        <Route path={AUTH_PATH()} element={<Authentication />} />
        <Route path={BOARD_WRITE()} element={<BoardWrite />} />
        <Route path={SIGN_IN_PATH()} element={<SignIn />} />
        <Route path={SIGN_UP_PATH()} element={<SignUp />} />
        {/* <Route path={AUTH_PATH()} element={<Authentication />} /> */}
      </Route>
    </Routes>
  );
}

export default App;
