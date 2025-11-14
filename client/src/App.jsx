import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Login from './pages/login'
import Home from './pages/Home'
import GenText from './pages/GenText'
import Navbar from './components/Navbar'
import ReWrite from './pages/ReWrite'

const App = () => {
  return (
    <>
    {/* <Navbar/> */}
     <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Home />} />
        <Route path="/text" element={<GenText />} />
        <Route path="/write" element={<ReWrite />} />
      </Routes>
    </>
  )
}

export default App
