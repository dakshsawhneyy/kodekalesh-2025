import { motion } from "framer-motion";
import { Link } from "react-router-dom";

const CTASection = () => {
  return (
    <section className="w-full py-28 bg-linear-to-br from-[#0d0d0f] via-[#1b1b1e] to-[#0f0f10] px-6 relative">

      {/* Soft Background Glows */}
      <div className="absolute inset-0 overflow-hidden pointer-events-none">
        <div className="absolute top-10 left-10 w-72 h-72 bg-purple-600/20 blur-[120px] rounded-full"></div>
        <div className="absolute bottom-10 right-10 w-80 h-80 bg-pink-600/20 blur-[130px] rounded-full"></div>
      </div>

      <motion.div
        initial={{ opacity: 0, scale: 0.95 }}
        whileInView={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.7 }}
        viewport={{ once: true }}
        className="relative max-w-4xl mx-auto text-center backdrop-blur-2xl bg-white/5 border border-white/10 rounded-3xl shadow-2xl p-12"
      >
        
        {/* Main Headline */}
        <h2 className="text-4xl md:text-5xl font-bold bg-linear-to-r from-purple-400 to-pink-400 bg-clip-text text-transparent mb-6">
          Start Creating Smarter Content Today
        </h2>

        {/* Subtext */}
        <p className="text-white/70 text-lg md:text-xl max-w-2xl mx-auto mb-10">
          KreatoR helps you generate high-quality notes, captions, ads, blogs, 
          rewrites and more — all perfectly structured and ready in seconds.
        </p>

        {/* CTA Buttons */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.7, delay: 0.3 }}
          viewport={{ once: true }}
          className="flex flex-col md:flex-row items-center justify-center gap-6"
        >
          {/* Generate Texts */}
          <Link
            to="/text"
            className="px-10 py-4 rounded-xl bg-linear-to-r from-purple-600 to-pink-600 text-white font-semibold text-lg shadow-xl hover:opacity-90 transition inline-block"
          >
            Generate texts →
          </Link>

          {/* Rewrite Texts */}
          <Link
            to="/write"
            className="px-10 py-4 rounded-xl bg-linear-to-r from-pink-600 to-purple-600 text-white font-semibold text-lg shadow-xl hover:opacity-90 transition inline-block"
          >
            Rewrite texts →
          </Link>
        </motion.div>

      </motion.div>
    </section>
  );
}

export default CTASection;
