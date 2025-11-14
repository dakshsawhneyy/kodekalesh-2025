import React, { useState } from "react";
import { motion } from "framer-motion";
import axios from "axios";

const FeedbackForm = () => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    rating: "",
    comments: "",
  });

  const [submitted, setSubmitted] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitted(false);

    try {
      const response = await axios.post(
        "https://rv6p8wji3k.execute-api.ap-south-1.amazonaws.com/kode-kalesh/reviews",
        form
      );

      let data = response.data;

      // Remove  if API returns markdown
      if (typeof data === "string") {
        data = data.replace(/```/g, "").trim();
      }

      // Try to parse JSON
      try {
        data = JSON.parse(data);
      } catch (err) {
        console.log("Not JSON, using raw string instead:", data);
      }

      console.log("Feedback submitted:", data);
      setSubmitted(true);

    } catch (error) {
      console.log("Error:", error.message);
      alert("Something went wrong while submitting feedback.");
    }
  };

  return (
    <div className="min-h-screen w-full flex justify-center items-start p-10 bg-linear-to-br from-[#0f0f1f] via-[#161628] to-[#0c0c18] relative">
      <div className="w-full max-w-3xl space-y-8">

        <button
          onClick={() => (window.location.href = "/")}
          className="absolute top-6 left-6 p-3 rounded-xl bg-white/10 text-white border border-white/20 hover:bg-white/20 backdrop-blur-xl shadow-md"
        >
          ←
        </button>

        <h1 className="text-4xl font-semibold text-white text-center">
          Feedback Form
        </h1>

        <motion.form
          onSubmit={handleSubmit}
          whileHover={{ scale: 1.01 }}
          className="backdrop-blur-xl bg-white/10 p-8 border border-white/20 rounded-2xl shadow-lg space-y-6"
        >
          <div>
            <label className="block mb-2 text-sm text-gray-300">Name</label>
            <input
              type="text"
              name="name"
              value={form.name}
              onChange={handleChange}
              placeholder="Your name"
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm text-gray-300">Email</label>
            <input
              type="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              placeholder="Your email"
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            />
          </div>

          <div>
            <label className="block mb-2 text-sm text-gray-300">Rating</label>
            <select
              name="rating"
              value={form.rating}
              onChange={handleChange}
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            >
              <option value="">Select Rating</option>
              <option value="1">1 - Poor</option>
              <option value="2">2 - Fair</option>
              <option value="3">3 - Good</option>
              <option value="4">4 - Very Good</option>
              <option value="5">5 - Excellent</option>
            </select>
          </div>

          <div>
            <label className="block mb-2 text-sm text-gray-300">Comments</label>
            <textarea
              name="review_text"
              rows={4}
              value={form.review_text}
              onChange={handleChange}
              placeholder="Share your feedback..."
              className="w-full p-3 rounded-xl bg-[#1b1b2d] text-white border border-white/20"
            />
          </div>

          <motion.button
            whileTap={{ scale: 0.97 }}
            whileHover={{ scale: 1.03 }}
            type="submit"
            className="w-full py-4 rounded-xl bg-blue-500/80 hover:bg-blue-600 text-white text-lg"
          >
            Submit Feedback
          </motion.button>
        </motion.form>

        {submitted && (
          <div className="mt-6 p-6 bg-white/10 border border-white/20 rounded-2xl text-white text-center">
            <h2 className="text-2xl font-semibold mb-2">Thank you!</h2>
            <p className="text-gray-300">
              Your feedback has been submitted successfully.
            </p>
          </div>
        )}
      </div>
    </div>
  );
};

export default FeedbackForm;