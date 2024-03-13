import React, { useEffect } from "react";
import logo from "./logo.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Container from "./layouts/Container";
import { AUTH_PATH, MAIN_PATH, SIGN_IN_PATH, SIGN_UP_PATH } from "./constant";
import Main from "./views/Main";
import Authentication from "./views/Authentication";
import SignIn from "./views/Authentication/sign-in";
import SignUp from "./views/Authentication/sign-up";
import { useCookies } from "react-cookie";
import { useLoginUserStore } from "./store";

function App() {
  const { setLoginUser, resetLoginUser } = useLoginUserStore();
  const [cookies, setCookies] = useCookies();

  useEffect(() => {
    if (!cookies.accessToken) {
      resetLoginUser();
      return;
    }
  }, [cookies.accessToken]);
  return (
    <Routes>
      <Route element={<Container />}>
        <Route path={MAIN_PATH()} element={<Main />} />
        <Route path={AUTH_PATH()} element={<Authentication />} />

        <Route path={SIGN_IN_PATH()} element={<SignIn />} />
        <Route path={SIGN_UP_PATH()} element={<SignUp />} />
        {/* <Route path={AUTH_PATH()} element={<Authentication />} /> */}
      </Route>
    </Routes>
  );
}

export default App;
