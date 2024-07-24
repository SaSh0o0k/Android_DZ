import { useQuery, UseQueryResult } from 'react-query'
import axios from 'axios'
import { BASE_URL } from '@env'
import { IPizza } from '@/interfaces/pizza'

const usePizzasByCategory = (
  categoryId: number,
): UseQueryResult<IPizza[], Error> => {
  return useQuery('pizzas', async () => {
    const response = await axios.get(
      `http://10.0.2.2:5174/api/Pizza/GetPage?CategoryId=${categoryId}`,
    )
    return response.data.data as IPizza[]
  })
}

export default usePizzasByCategory
