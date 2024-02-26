import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import Container from './layouts/Container';
import { MAIN_PATH } from './constant';

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
