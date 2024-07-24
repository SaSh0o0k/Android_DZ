import { useMutation, UseMutationResult, MutationFunction } from 'react-query'
import axios from 'axios'
import { BASE_URL } from '@env'
import { IUserCreate } from '@/interfaces/account'

const useCreateUser = (): UseMutationResult<
  void,
  Error,
  IUserCreate,
  unknown
> => {
  const createUser: MutationFunction<void, IUserCreate> = async (
    userData: IUserCreate,
  ) => {
    const formData = new FormData()
    formData.append('firstName', userData.firstName)
    formData.append('lastName', userData.lastName)
    formData.append('userName', userData.lastName + userData.firstName)

    formData.append('email', userData.email)
    formData.append('password', userData.password)

    // if (userData.image) formData.append('image', userData.image)

    await axios.post(
      `http://10.0.2.2:5174/api/accounts/registration`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      },
    )
  }

  return useMutation(createUser)
}

export default useCreateUser
