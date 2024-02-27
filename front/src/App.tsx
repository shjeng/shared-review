<<<<<<< HEAD
import React from "react";
import logo from "./logo.svg";
import "./App.css";
=======
import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import Container from './layouts/Container';
import { MAIN_PATH } from './constant';
>>>>>>> 4885e4d025715ab12e2988cda83fc92025a5e4f8

function App() {

  return ( 
    <Routes>
      <Route element={<Container/>}>
        <Route path={MAIN_PATH()} element={<></>} />
      </Route>
    </Routes>
    )
}

export default App;
