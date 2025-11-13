import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Login from './pages/login'
import Home from './pages/Home'
import Dashboard from './pages/Dashboard'

const App = () => {
  return (
    <>
     <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Home />} />
        <Route path="/dash" element={<Dashboard />} />
      </Routes>
    </>
  )
}

export default App
