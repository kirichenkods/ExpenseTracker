package com.example.expensetracker.service;

import com.example.expensetracker.dto.OperationDTO;
import com.example.expensetracker.entity.Operation;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.exception.OperationNotFoundException;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.mapper.OperationMapper;
import com.example.expensetracker.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationMapper mapper;
    private final OperationRepository repository;
    private final UserService userService;

    /**
     * Создает новую операцию на основе {@link OperationDTO}.
     *
     * @param operationDTO с данными операции, включая идентификатор пользователя
     * @return UUID созданной операции
     * @throws UserNotFoundException если пользователь с заданным ID не найден
     */
    @Override
    public UUID createOperation(OperationDTO operationDTO) throws UserNotFoundException {
        User user = userService.getUserById(operationDTO.getUser_id());
        Operation op = mapper.toEntity(operationDTO, user);
        Operation opSaved = repository.save(op);
        return opSaved.getId();
    }

    /**
     * Вспомогательный метод для получения операции по идентификатору.
     *
     * @param id UUID операции
     * @return Операция {@link Operation}
     * @throws OperationNotFoundException если операция с заданным ID не найдена
     */
    private Operation getOperationById(UUID id) throws OperationNotFoundException {
        Optional<Operation> op = repository.getOperationById(id);
        return op.orElseThrow(
                () -> new OperationNotFoundException("операция с id " + id  + " не найдена"));
    }

    /**
     * Удаляет операцию по ее UUID.
     *
     * @param uuid UUID операции для удаления
     */
    @Override
    public void deleteOperation(UUID uuid) {
        repository.deleteById(uuid);
    }

    /**
     * Обновляет данные операции на основе переданной DTO.
     *
     * @param dto DTO с обновленными данными операции
     * @throws UserNotFoundException если пользователь с заданным ID не найден
     * @throws OperationNotFoundException если операция для обновления не найдена
     */
    @Override
    public void updateOperation(OperationDTO dto) throws UserNotFoundException,
            OperationNotFoundException {
        User user = userService.getUserById(dto.getUser_id());
        Operation oldOp = getOperationById(dto.getId());
        Operation newOperation = mapper.toEntity(dto, user);
        repository.save(newOperation);
    }

    /**
     * Получает список всех операций пользователя по его UUID.
     *
     * @param userUuid UUID пользователя
     * @return Список DTO операций {@link OperationDTO}
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public List<OperationDTO> getUserOperations(UUID userUuid) throws UserNotFoundException {
        User user = userService.getUserById(userUuid);
        List<Operation> operations = repository.findOperationsByUserId(user.getId());
        return mapper.toDTOs(operations);
    }

    /**
     * Получает список операций пользователя фильтрованных по признаку дохода/расхода.
     *
     * @param userUuid UUID пользователя
     * @param isIncome true для доходов, false для расходов
     * @return Список DTO операций
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public List<OperationDTO> getUserOperations(UUID userUuid, boolean isIncome)
            throws UserNotFoundException {
        User user = userService.getUserById(userUuid);
        List<Operation> operations =
                repository.findAllByUserIdAndIsIncome(user.getId(), isIncome);
        return mapper.toDTOs(operations);
    }

    /**
     * Вычисляет сумму всех операций пользователя, фильтруя по признаку дохода/расхода.
     *
     * @param userUuid UUID пользователя
     * @param isIncome true для доходов, false для расходов
     * @return сумма операций
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public double getSumAmountByUser(UUID userUuid, boolean isIncome)
            throws UserNotFoundException {
        User user = userService.getUserById(userUuid);
        return repository.getSumAmountByUserId(user.getId(), isIncome);
    }

    /**
     * Получает список операций пользователя за указанный период.
     *
     * @param userUuid UUID пользователя
     * @param dateStart дата начала периода
     * @param dateEnd дата конца периода
     * @return Список операций, попадающих в период
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                        LocalDate dateStart,
                                                        LocalDate dateEnd)
            throws UserNotFoundException {
        User user = userService.getUserById(userUuid);
        List<Operation> operations =
                repository.findAllByUserIdAndDateBetween(
                        user.getId(),
                        dateStart,
                        dateEnd);
        return mapper.toDTOs(operations);
    }


    /**
     * Получает список операций пользователя за период с фильтром по типу операции.
     *
     * @param userUuid UUID пользователя
     * @param dateStart дата начала периода
     * @param dateEnd дата конца периода
     * @param isIncome true для доходов, false для расходов
     * @return Список отфильтрованных по типу операций
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public List<OperationDTO> getUserOperationsByPeriod(UUID userUuid,
                                                        LocalDate dateStart,
                                                        LocalDate dateEnd,
                                                        boolean isIncome)
            throws UserNotFoundException {
        User user = userService.getUserById(userUuid);
        List<Operation> operations =
                repository.findAllByUserIdAndIncomeAndDateBetween(
                        user.getId(),
                        dateStart,
                        dateEnd,
                        isIncome);
        return mapper.toDTOs(operations);
    }

    /**
     * Получает список всех операций без фильтра.
     *
     * @return Список всех операций в виде DTO
     */
    @Override
    public List<OperationDTO> getOperations() {
        List<Operation> operations = repository.findAll();
        return mapper.toDTOs(operations);
    }
}
