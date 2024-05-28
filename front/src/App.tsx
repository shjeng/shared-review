import React, { useEffect } from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Container from "./layouts/Container";
import {
  ADMIN_BOARD_LIST,
  AUTH_PATH,
  BOARD_DETAIL,
  BOARD_LIST,
  BOARD_WRITE,
  CATEGORI_MANAGE_PATH,
  MAIN_PATH,
  SIGN_IN_PATH,
  SIGN_UP_PATH,
  USER_BOARD,
  USER_MANAGE_PATH,
  USER_PAGE_PATH,
} from "./constant";
import Main from "./views/Main";
import Authentication from "./views/Authentication";
import SignIn from "./views/Authentication/sign-in";
import SignUp from "./views/Authentication/sign-up";
import { useCookies } from "react-cookie";
import { useLoginUserStore } from "./store";
import { getMyInfo } from "./apis";
import { GetUserResponseDto } from "./apis/response/user";
import ResponseDto from "./apis/response/response.dto";
import BoardWrite from "./views/BoardWrite";
import BoardList from "./views/BoardList";
import UserPage from "./views/UserPage";
import BoardDetail from "./views/BoardDetail";
import AdminBoardList from "./views/Admin/AdminBoardList";
import AdminUserList from "./views/Admin/UserList";
import UserBoard from "./views/UserBoard";
import AdminCategories from "./views/Admin/Categories";

function App() {
  const { setLoginUser, resetLoginUser } = useLoginUserStore();
  const [cookies, setCookies] = useCookies();

  useEffect(() => {
    if (!cookies.accessToken) {
      resetLoginUser();
      return;
    }
    getMyInfo(cookies.accessToken).then(getLoginUserResponse);
  }, [cookies.accessToken]);
  const getLoginUserResponse = (
    responseBody: GetUserResponseDto | ResponseDto | null
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
    const { userDto } = responseBody as GetUserResponseDto;
    setLoginUser(userDto);
  };
  return (
    <Routes>
      <Route element={<Container />}>
        <Route path={MAIN_PATH()} element={<Main />} />
        <Route path={AUTH_PATH()} element={<Authentication />} />
        <Route path={BOARD_WRITE()} element={<BoardWrite />} />
        <Route path={BOARD_DETAIL(":boardId")} element={<BoardDetail />} />
        <Route path={BOARD_LIST()} element={<BoardList />} />
        <Route path={SIGN_IN_PATH()} element={<SignIn />} />
        <Route path={SIGN_UP_PATH()} element={<SignUp />} />
        <Route path={USER_PAGE_PATH(":userEmail")} element={<UserPage />} />
        <Route path={USER_BOARD(":userEmail")} element={<UserBoard />} />
        <Route path={USER_MANAGE_PATH()} element={<AdminUserList />} />
        <Route path={CATEGORI_MANAGE_PATH()} element={<AdminCategories />} />
        <Route path={ADMIN_BOARD_LIST()} element={<AdminBoardList />} />

        {/* <Route path={AUTH_PATH()} element={<Authentication />} /> */}
      </Route>
    </Routes>
  );
}

export default App;
