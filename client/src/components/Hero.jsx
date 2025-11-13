import React from 'react'
import { motion } from "framer-motion";

const Hero = () => {
return (
    <div className="min-h-screen bg-linear-to-br from-[#0d0d0f] via-[#1b1b1e]
     to-[#0f0f10] flex items-center justify-center px-6 pt-20">

      <div className="text-center max-w-3xl">

        {/* Animated Title */}
        <motion.h1
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
          className="text-6xl md:text-6xl font-bold tracking-tight bg-linear-to-r
           from-purple-400 to-pink-400 text-transparent bg-clip-text drop-shadow-xl mb-6"
        >
          Create Content.  
          <span className="block mt-2">Smarter, Faster, Better.</span>
        </motion.h1>

        {/* Subtext */}
        <motion.p
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5, duration: 0.8 }}
          className="text-white/60 text-lg md:text-xl leading-relaxed mb-10"
        >
          cGen is your AI-powered content generation platform.
          <br />  
          Instantly generate educational notes, blog articles, IG captions, ads, and more — all perfectly structured.
        </motion.p>

        {/* Animated Buttons */}
        {/* <motion.div
          initial={{ opacity: 0, y: 25 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.8, duration: 0.7 }}
          className="flex justify-center gap-4"
        >
          <a
            href="/dashboard"
            className="px-7 py-3 rounded-xl bg-linear-to-r from-purple-600 to-pink-600 text-white font-semibold shadow-xl hover:opacity-90 transition"
          >
            Try Now
          </a>

          <a
            href="/login"
            className="px-7 py-3 rounded-xl border border-white/20 text-white/80 hover:bg-white/10 transition backdrop-blur-xl"
          >
            Login
          </a>
        </motion.div> */}

        {/* Soft glowing orbs */}
        <div className="relative">
          <div className="absolute -top-16 -left-20 w-40 h-40 bg-purple-600/30 rounded-full blur-3xl animate-pulse"></div>
          <div className="absolute -bottom-20 -right-20 w-52 h-52 bg-pink-600/20 rounded-full blur-3xl animate-pulse"></div>
        </div>

      </div>
    </div>
  );
}

export default Hero
