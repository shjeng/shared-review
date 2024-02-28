import React, { ChangeEvent, KeyboardEvent, useRef, useState } from "react";
import "./style.css";

const Authentication = () => {
  //          state: 화면 상태        //
  const [view, setView] = useState<"sign-in" | "sign-up">("sign-in");

  const SignIn = () => {
    return (
      <div>
        <div className="auth-sign-in-top">
          <input type="text" name="userName" placeholder="Email" />
          <input type="password" name="userPassword" placeholder="Password" />
          <input type="submit" value="Login" />
        </div>

        <div className="auth-sign-in-bottom"></div>
      </div>
    );
  };

  const SignUp = () => {
    return <div></div>;
  };

  return (
    <div id="auth-wrapper">
      <div className="auth-top-box">
        <div className="auth-title">
          {view === "sign-in" && "로그인"}
          {view === "sign-up" && "회원가입"}
        </div>
      </div>
      <div className="auth-body-box">
        {view === "sign-in" && <SignIn />}
        {view === "sign-up" && <SignUp />}
      </div>
    </div>
  );
};

export default Authentication;
