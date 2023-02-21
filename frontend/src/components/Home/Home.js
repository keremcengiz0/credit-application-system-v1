import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
  let navigate = useNavigate();

  useEffect(() => {
    navigate("/applications/create-customer-and-make-application");
  }, []);
};

export default Home;
