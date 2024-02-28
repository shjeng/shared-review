import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Container from "./layouts/Container";
import { AUTH_PATH, MAIN_PATH } from "./constant";
import Main from "./views/Main";
import Authentication from "./views/Authentication";

function App() {
  return (
    <Routes>
      <Route element={<Container />}>
        <Route path={MAIN_PATH()} element={<Main />} />
        <Route path={AUTH_PATH()} element={<Authentication />} />
      </Route>
    </Routes>
  );
}

export default App;
