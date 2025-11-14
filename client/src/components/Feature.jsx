import React from 'react'
import { motion } from "framer-motion";

const Feature = () => {
  return (
    <section className="w-full py-24 bg-linear-to-br from-[#0d0d0f] via-[#1b1b1e] to-[#0f0f10] px-6">
      
      {/* Title */}
      <motion.h2
        initial={{ opacity: 0, y: 20 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.6 }}
        viewport={{ once: true }}
        className="text-5xl md:text-5xl font-bold text-center mb-4 
        bg-linear-to-r from-purple-400 to-pink-400 bg-clip-text text-transparent"
      >
        Powerful Features
      </motion.h2>

      <motion.p
        initial={{ opacity: 0 }}
        whileInView={{ opacity: 1 }}
        transition={{ duration: 0.8, delay: 0.2 }}
        viewport={{ once: true }}
        className="text-white/70 text-center max-w-2xl mx-auto mb-14 text-lg"
      >
        Crafted to deliver precise, structured and platform-ready content —  
        whether it’s refined text, rewrites or sharp AI-generated visuals.
      </motion.p>

      {/* Feature Cards */}
      <div className="grid md:grid-cols-3 gap-10 max-w-6xl mx-auto">

        {/* Education */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6 }}
          viewport={{ once: true }}
          className="p-8 rounded-2xl backdrop-blur-xl bg-white/5 border border-white/10 shadow-xl 
          hover:bg-white/10 transition cursor-pointer"
        >
          <h3 className="text-2xl font-semibold text-white mb-4">
             Education Content
          </h3>
          <p className="text-white/60 leading-relaxed">
            Generate clean, structured notes, bullet points, examples,  
            and MCQs for any topic with accurate, filtered output.
          </p>
        </motion.div>

        {/* Social Media */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6, delay: 0.1 }}
          viewport={{ once: true }}
          className="p-8 rounded-2xl backdrop-blur-xl bg-white/5 border border-white/10 shadow-xl 
          hover:bg-white/10 transition cursor-pointer"
        >
          <h3 className="text-2xl font-semibold text-white mb-4">
             Social Media
          </h3>
          <p className="text-white/60 leading-relaxed">
            Rewrite or refine text into platform-ready captions, hooks,  
            hashtags and short-form content that matches your style.
          </p>
        </motion.div>

        {/* Marketing */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6, delay: 0.2 }}
          viewport={{ once: true }}
          className="p-8 rounded-2xl backdrop-blur-xl bg-white/5 border border-white/10 shadow-xl 
          hover:bg-white/10 transition cursor-pointer"
        >
          <h3 className="text-2xl font-semibold text-white mb-4">
             Marketing & Ads
          </h3>
          <p className="text-white/60 leading-relaxed">
            Produce sharp, high-intent ad copies with hooks,  
            benefits and clear CTAs for better conversions.
          </p>
        </motion.div>

      </div>

    </section>
  );
}

export default Feature;