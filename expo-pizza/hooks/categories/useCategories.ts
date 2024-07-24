import { useQuery, UseQueryResult } from 'react-query'
import axios from 'axios'
import { ICategory } from '@/interfaces/category'
import { BASE_URL } from '@env'

const useCategories = (): UseQueryResult<ICategory[], Error> => {
  return useQuery('categories', async () => {
    const response = await axios.get(
      `https://pizza-api-max.tohaproject.click/api/categories/getAll`,
    )
    return response.data as ICategory[]
  })
}

export default useCategories
